package cxf.transport.custom_conduit;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

import org.apache.cxf.Bus;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.io.AbstractWrappedOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.AbstractConduit;
import org.apache.cxf.transport.AbstractDestination;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.local.LocalConduit;
import org.apache.cxf.transport.local.LocalDestination;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.apache.cxf.workqueue.SynchronousExecutor;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public class CustomDestination extends AbstractDestination{
	
	private static final Logger LOG = LogUtils.getL7dLogger(CustomDestination.class);
	
	private CustomTransportFactory customDestinationFactory;

	public CustomDestination(CustomTransportFactory localDestinationFactory, EndpointReferenceType epr, EndpointInfo ei, Bus bus) {
		super(bus, epr, ei);
		System.out.println("Called CustomDestination constructor");
		this.customDestinationFactory = localDestinationFactory;
	}

	@Override
	protected Conduit getInbuiltBackChannel(Message inMessage) {
		System.out.println("Called CustomDestination.getInbuiltBackChannel");
		Conduit conduit = (Conduit)inMessage.get(LocalConduit.IN_CONDUIT);
		return conduit instanceof CustomConduit ? new CustomDestination.SynchronousConduit((CustomConduit)conduit) : null;
	}

	@Override
	protected Logger getLogger() {
		System.out.println("Called CustomDestination.getLogger");
		return LOG;
	}
	
	@Override
	public void shutdown() {
		System.out.println("Called CustomDestination.shutdown");
		this.customDestinationFactory.remove(this);
		super.shutdown();
	}

	public Bus getBus() {
		return this.bus;
	}
	//
	class SynchronousConduit extends AbstractConduit {
		private CustomConduit conduit;

		SynchronousConduit(CustomConduit conduit) {
			super((EndpointReferenceType) null);
			this.conduit = conduit;
		}

		public void prepare(Message message) throws IOException {
			if (!Boolean.TRUE.equals(message.getExchange().get(LocalConduit.DIRECT_DISPATCH))) {
				Exchange exchange = (Exchange) message.getExchange().get(LocalConduit.IN_EXCHANGE);
				AbstractWrappedOutputStream cout = new CustomDestination.SynchronousConduit.CustomDestinationOutputStream(exchange, message);
				message.setContent(OutputStream.class, cout);
			} else {
				CachedOutputStream stream = new CachedOutputStream();
				message.setContent(OutputStream.class, stream);
				message.setContent(CachedOutputStream.class, stream);
				stream.holdTempFile();
			}

		}

		public void close(Message message) throws IOException {
			Integer i = (Integer) message.get(Message.RESPONSE_CODE);
			if (i == null) {
				int code = (!message.getExchange().isOneWay() || MessageUtils.isPartialResponse(message)) && !MessageUtils.isEmptyPartialResponse(message) ? 200 : 202;
				message.put(Message.RESPONSE_CODE, code);
			}

			if (Boolean.TRUE.equals(message.getExchange().get(LocalConduit.DIRECT_DISPATCH))) {
				Exchange exchange = (Exchange) message.getExchange().get(LocalConduit.IN_EXCHANGE);
				MessageImpl copy = new MessageImpl();
				copy.putAll(message);
				((OutputStream) message.getContent(OutputStream.class)).close();
				CachedOutputStream stream = (CachedOutputStream) message.getContent(CachedOutputStream.class);
				message.setContent(OutputStream.class, stream);
				MessageImpl.copyContent(message, copy);
				copy.setContent(InputStream.class, stream.getInputStream());
				stream.releaseTempFileHold();
				if (exchange != null && exchange.getInMessage() == null) {
					exchange.setInMessage(copy);
				}

				this.conduit.getMessageObserver().onMessage(copy);
			} else {
				super.close(message);
			}
		}

		protected Logger getLogger() {
			return CustomDestination.LOG;
		}

		private final class CustomDestinationOutputStream extends AbstractWrappedOutputStream {
			private final Exchange exchange;
			private final Message message;

			private CustomDestinationOutputStream(Exchange exchange, Message message) {
				this.exchange = exchange;
				this.message = message;
			}

			public void close() throws IOException {
				if (!this.written) {
					this.dispatchToClient(true);
				}

				super.close();
			}

			protected void onFirstWrite() throws IOException {
				this.dispatchToClient(false);
			}

			protected void dispatchToClient(boolean empty) throws IOException {
				final MessageImpl m = new MessageImpl();
				CustomDestination.this.customDestinationFactory.copy(this.message, m);
				if (!empty) {
					PipedInputStream stream = new PipedInputStream();
					this.wrappedStream = new PipedOutputStream(stream);
					m.setContent(InputStream.class, stream);
				}

				Runnable receiver = new Runnable() {
					public void run() {
						if (CustomDestination.SynchronousConduit.CustomDestinationOutputStream.this.exchange != null) {
							CustomDestination.SynchronousConduit.CustomDestinationOutputStream.this.exchange.setInMessage(m);
						}

						CustomDestination.SynchronousConduit.this.conduit.getMessageObserver().onMessage(m);
					}
				};
				Executor ex = this.message.getExchange() != null ? (Executor) this.message.getExchange().get(Executor.class) : null;
				if (ex != null && !SynchronousExecutor.isA(ex)) {
					ex.execute(receiver);
				} else {
					if (this.exchange == null) {
						ex = CustomDestination.this.customDestinationFactory.getExecutor(CustomDestination.this.bus);
					} else {
						ex = CustomDestination.this.customDestinationFactory.getExecutor(this.exchange.getBus());
					}

					if (ex != null) {
						ex.execute(receiver);
					} else {
						(new Thread(receiver)).start();
					}
				}

			}
		}
	}
}

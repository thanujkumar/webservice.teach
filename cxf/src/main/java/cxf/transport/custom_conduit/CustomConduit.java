package cxf.transport.custom_conduit;

import org.apache.cxf.Bus;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.io.AbstractWrappedOutputStream;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.*;
import org.apache.cxf.transport.AbstractConduit;
import org.apache.cxf.workqueue.SynchronousExecutor;

import java.io.*;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public class CustomConduit extends AbstractConduit {

	public static final String IN_CONDUIT = CustomConduit.class.getName() + ".inConduit";
	public static final String RESPONSE_CONDUIT = CustomConduit.class.getName() + ".inConduit";
	public static final String IN_EXCHANGE = CustomConduit.class.getName() + ".inExchange";
	public static final String DIRECT_DISPATCH = CustomConduit.class.getName() + ".directDispatch";
	public static final String MESSAGE_FILTER_PROPERTIES;
	private static final Logger LOG;
	private CustomDestination destination;
	private CustomTransportFactory transportFactory;

	public CustomConduit(CustomTransportFactory transportFactory, CustomDestination destination) {
		super(destination.getAddress());
		this.destination = destination;
		this.transportFactory = transportFactory;
	}

	public void prepare(Message message) throws IOException {
		if (!MessageUtils.getContextualBoolean(message, DIRECT_DISPATCH)) {
			this.dispatchViaPipe(message);
		} else {
			CachedOutputStream stream = new CachedOutputStream();
			message.setContent(OutputStream.class, stream);
			message.put(CachedOutputStream.class, stream);
			stream.holdTempFile();
		}

	}

	public void close(Message message) throws IOException {
		if (MessageUtils.getContextualBoolean(message, DIRECT_DISPATCH) && !Boolean.TRUE.equals(message.get("org.apache.cxf.message.inbound"))) {
			this.dispatchDirect(message);
		}

		super.close(message);
	}

	private void dispatchDirect(Message message) throws IOException {
		if (this.destination.getMessageObserver() == null) {
			throw new IllegalStateException("Custom destination does not have a MessageObserver on address " + this.destination.getAddress().getAddress().getValue());
		} else {
			MessageImpl copy = new MessageImpl();
			copy.put(IN_CONDUIT, this);
			copy.setDestination(this.destination);
			this.transportFactory.copy(message, copy);
			MessageImpl.copyContent(message, copy);
			OutputStream out = (OutputStream)message.getContent(OutputStream.class);
			out.flush();
			out.close();
			CachedOutputStream stream = (CachedOutputStream)message.get(CachedOutputStream.class);
			copy.setContent(InputStream.class, stream.getInputStream());
			copy.removeContent(CachedOutputStream.class);
			stream.releaseTempFileHold();
			ExchangeImpl ex = new ExchangeImpl();
			ex.setInMessage(copy);
			ex.put(IN_EXCHANGE, message.getExchange());
			ex.put(DIRECT_DISPATCH, true);
			ex.setDestination(this.destination);
			this.destination.getMessageObserver().onMessage(copy);
		}
	}

	private void dispatchViaPipe(Message message) throws IOException {
		Exchange exchange = message.getExchange();
		if (this.destination.getMessageObserver() == null) {
			throw new IllegalStateException("Custom destination does not have a MessageObserver on address " + this.destination.getAddress().getAddress().getValue());
		} else {
			AbstractWrappedOutputStream cout = new CustomConduit.CustomConduitOutputStream(this, exchange, message);
			message.setContent(OutputStream.class, cout);
		}
	}

	protected Logger getLogger() {
		return LOG;
	}

	static {
		MESSAGE_FILTER_PROPERTIES = CustomTransportFactory.MESSAGE_FILTER_PROPERTIES;
		LOG = LogUtils.getL7dLogger(CustomConduit.class);
	}

	private final class CustomConduitOutputStream extends AbstractWrappedOutputStream {
		private final CustomConduit conduit;
		private final Exchange exchange;
		private final Message message;

		private CustomConduitOutputStream(CustomConduit conduit, Exchange exchange, Message message) {
			this.conduit = conduit;
			this.exchange = exchange;
			this.message = message;
		}

		public void close() throws IOException {
			if (!this.written) {
				this.dispatchToService(true);
			}

			super.close();
		}

		protected void onFirstWrite() throws IOException {
			this.dispatchToService(false);
		}

		protected void dispatchToService(boolean empty) throws IOException {
			final MessageImpl inMsg = new MessageImpl();
			CustomConduit.this.transportFactory.copy(this.message, inMsg);
			if (!empty) {
				PipedInputStream stream = new PipedInputStream();
				this.wrappedStream = new PipedOutputStream(stream);
				inMsg.setContent(InputStream.class, stream);
			}

			inMsg.setDestination(CustomConduit.this.destination);
			inMsg.put(CustomConduit.IN_CONDUIT, this.conduit);
			Runnable receiver = new Runnable() {
				public void run() {
					ExchangeImpl ex = new ExchangeImpl();
					ex.put(Bus.class, CustomConduit.this.destination.getBus());
					ex.setInMessage(inMsg);
					inMsg.setExchange(ex);
					ex.put(CustomConduit.IN_EXCHANGE, CustomConduit.CustomConduitOutputStream.this.exchange);

					try {
						CustomConduit.this.destination.getMessageObserver().onMessage(inMsg);
					} catch (Throwable var6) {
						Message m = inMsg.getExchange().getOutFaultMessage();
						if (m == null) {
							m = inMsg.getExchange().getOutMessage();
						}

						if (m != null) {
							try {
								m.put(Message.RESPONSE_CODE, 500);
								m.put(Message.PROTOCOL_HEADERS, new HashMap());
								m.getExchange().put(Message.RESPONSE_CODE, 500);
								((OutputStream)m.getContent(OutputStream.class)).close();
							} catch (IOException var5) {
							}
						}
					}

				}
			};
			Executor ex = this.message.getExchange() != null ? (Executor)this.message.getExchange().get(Executor.class) : null;
			if (ex != null && !SynchronousExecutor.isA(ex)) {
				ex.execute(receiver);
			} else {
				ex = CustomConduit.this.transportFactory.getExecutor(CustomConduit.this.destination.getBus());
				if (ex != null) {
					ex.execute(receiver);
				} else {
					(new Thread(receiver)).start();
				}
			}

		}
	}
}

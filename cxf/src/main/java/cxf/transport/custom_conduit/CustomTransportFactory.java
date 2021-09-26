package cxf.transport.custom_conduit;

import org.apache.cxf.Bus;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.*;
import org.apache.cxf.transport.local.LocalConduit;
import org.apache.cxf.transport.local.LocalDestination;
import org.apache.cxf.workqueue.WorkQueueManager;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.wsdl.http.AddressType;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

public class CustomTransportFactory extends AbstractTransportFactory implements ConduitInitiator, DestinationFactory {

	public static final String TRANSPORT_ID = "http://cxf.apache.org/transports/tk";
	public static final List<String> DEFAULT_NAMESPACES = Arrays.asList(TRANSPORT_ID);
	private static final Logger LOG = LogUtils.getL7dLogger(CustomTransportFactory.class);
	private static final Set<String> URI_PREFIXES = new HashSet<>();
	private Set<String> uriPrefixes;
	private Set<String> messageFilterProperties;
	private Set<String> messageIncludeProperties;
	private static final String NULL_ADDRESS = CustomTransportFactory.class.getName() + ".nulladdress";
	private ConcurrentMap<String, Destination> destinations = new ConcurrentHashMap();
	public static final String MESSAGE_FILTER_PROPERTIES = CustomTransportFactory.class.getName() + ".filterProperties";
	public static final String MESSAGE_INCLUDE_PROPERTIES = CustomTransportFactory.class.getName() + ".includeProperties";
	private volatile Executor executor;

	static {
		URI_PREFIXES.add("tk://");
	}

	public CustomTransportFactory() {
		super(DEFAULT_NAMESPACES);
		this.uriPrefixes = new HashSet(URI_PREFIXES);
		this.messageFilterProperties = new HashSet();
		this.messageIncludeProperties = new HashSet();
		this.messageFilterProperties.add("org.apache.cxf.client");
		this.messageIncludeProperties.add(Message.PROTOCOL_HEADERS);
		this.messageIncludeProperties.add(Message.ENCODING);
		this.messageIncludeProperties.add("Content-Type");
		this.messageIncludeProperties.add("Accept");
		this.messageIncludeProperties.add(Message.RESPONSE_CODE);
		this.messageIncludeProperties.add("org.apache.cxf.request.uri");
		this.messageIncludeProperties.add(Message.ENDPOINT_ADDRESS);
		this.messageIncludeProperties.add("org.apache.cxf.request.method");
	}

	@Override
	public Destination getDestination(EndpointInfo ei, Bus bus) throws IOException {
		System.out.println("Called CustomTransportFactory.getDestination");
		return this.getDestination(ei, this.createReference(ei), bus);
	}

	@Override
	public Conduit getConduit(EndpointInfo targetInfo, Bus bus) throws IOException {
		System.out.println("Called CustomTransportFactory.getConduit");
		return new CustomConduit(this, (CustomDestination) this.getDestination(targetInfo, bus));
	}

	@Override
	public Conduit getConduit(EndpointInfo localInfo, EndpointReferenceType target, Bus bus) throws IOException {
		System.out.println("Called CustomTransportFactory.getConduit");
		// TODO Auto-generated method stub
		return null;
	}

	//////////// Internal methods
	EndpointReferenceType createReference(EndpointInfo ei) {
		EndpointReferenceType epr = new EndpointReferenceType();
		AttributedURIType address = new AttributedURIType();
		address.setValue(ei.getAddress());
		epr.setAddress(address);
		return epr;
	}

	protected Destination getDestination(EndpointInfo ei, EndpointReferenceType reference, Bus bus) throws IOException {
		Destination d = null;
		String addr = reference.getAddress().getValue();
		if (addr == null) {
			AddressType tp = (AddressType)ei.getExtensor(AddressType.class);
			if (tp != null) {
				addr = tp.getLocation();
			}
		}

		if (addr == null) {
			addr = NULL_ADDRESS;
		}

		d = (Destination)this.destinations.get(addr);
		if (d == null) {
			d = this.createDestination(ei, reference, bus);
			Destination tmpD = (Destination)this.destinations.putIfAbsent(addr, d);
			if (tmpD != null) {
				d = tmpD;
			}
		}

		return d;
	}

	private Destination createDestination(EndpointInfo ei, EndpointReferenceType reference, Bus bus) {
		LOG.info("Creating destination for address " + reference.getAddress().getValue());
		return new CustomDestination(this, reference, ei, bus);
	}

	void remove(CustomDestination destination) {
		Iterator var2 = this.destinations.entrySet().iterator();

		while(var2.hasNext()) {
			Map.Entry<String, Destination> e = (Map.Entry)var2.next();
			if (e.getValue() == destination) {
				this.destinations.remove(e.getKey());
			}
		}

	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public Executor getExecutor(Bus bus) {
		if (this.executor == null && bus != null) {
			WorkQueueManager manager = (WorkQueueManager)bus.getExtension(WorkQueueManager.class);
			if (manager != null) {
				Executor ex = manager.getNamedWorkQueue("local-transport");
				if (ex == null) {
					ex = manager.getAutomaticWorkQueue();
				}

				return ex;
			}
		}

		return this.executor;
	}

	public void copy(Message message, Message copy) {
		Set<String> filter = CastUtils.cast((Set)message.get(MESSAGE_FILTER_PROPERTIES));
		if (filter == null) {
			filter = this.messageFilterProperties;
		}

		Set<String> includes = CastUtils.cast((Set)message.get(MESSAGE_INCLUDE_PROPERTIES));
		if (includes == null) {
			includes = this.messageIncludeProperties;
		}

		Iterator var5 = message.entrySet().iterator();

		while(true) {
			Map.Entry e;
			do {
				if (!var5.hasNext()) {
					return;
				}

				e = (Map.Entry)var5.next();
			} while(!includes.contains(e.getKey()) && !this.messageIncludeProperties.contains(e.getKey()));

			if (!filter.contains(e.getKey())) {
				//copy.put(e.getKey(), e.getValue());
				copy.put((String) e.getKey(), e.getValue());
			}
		}
	}

}

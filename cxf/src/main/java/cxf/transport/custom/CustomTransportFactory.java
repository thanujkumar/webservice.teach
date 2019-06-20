package cxf.transport.custom;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.cxf.Bus;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.AbstractTransportFactory;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.transport.ConduitInitiator;
import org.apache.cxf.transport.Destination;
import org.apache.cxf.transport.DestinationFactory;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public class CustomTransportFactory extends AbstractTransportFactory implements ConduitInitiator, DestinationFactory {

	public static final String TRANSPORT_ID = "http://cxf.apache.org/transports/tk";
	public static final List<String> DEFAULT_NAMESPACE = Arrays.asList(TRANSPORT_ID);
	
	private static final Set<String> URI_PREFIXES = new HashSet<>();
	
	static {
		URI_PREFIXES.add("tk://");
	}
	
	@Override
	public Destination getDestination(EndpointInfo ei, Bus bus) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conduit getConduit(EndpointInfo targetInfo, Bus bus) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Conduit getConduit(EndpointInfo localInfo, EndpointReferenceType target, Bus bus) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}

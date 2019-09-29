package cxf.transport.custom;

import java.util.logging.Logger;

import org.apache.cxf.Bus;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.transport.AbstractDestination;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public class CustomDestination extends AbstractDestination{
	
	private static final Logger LOG = LogUtils.getL7dLogger(CustomDestination.class);
	
	private CustomTransportFactory destinationFactory;

	public CustomDestination(Bus b, EndpointReferenceType ref, EndpointInfo ei) {
		super(b, ref, ei);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Conduit getInbuiltBackChannel(Message inMessage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void shutdown() {
		//destinationFactory
		super.shutdown();
	}
}

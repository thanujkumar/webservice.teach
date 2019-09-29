package cxf.transport.custom;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.cxf.message.Message;
import org.apache.cxf.transport.AbstractConduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;

public class CustomConduit extends AbstractConduit {

	public CustomConduit(EndpointReferenceType t) {
		super(t);
	}

	@Override
	public void prepare(Message message) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected Logger getLogger() {
		// TODO Auto-generated method stub
		return null;
	}

}

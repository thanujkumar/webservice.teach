package jaxws.basic;

import java.io.PrintStream;
import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SoapLoggingHandler implements SOAPHandler<SOAPMessageContext> {

	// change this to logging if required
	private static PrintStream out = System.out;

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		logDetails(context);
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		logDetails(context);
		return true;
	}

	@Override
	public void close(MessageContext context) {
		// no cleanup - The MessageContext interface provides methods to manage a
		// property set. MessageContext properties enable handlers in a handler chain to
		// share processing related state.
	}

	@Override
	public Set<QName> getHeaders() {
		// Nothing
		return Collections.EMPTY_SET;
	}

	private void logDetails(SOAPMessageContext context) {
		Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outboundProperty.booleanValue()) {
			out.println("Outbound message:");
		} else {
			out.println("Inbound message:");
		}

		SOAPMessage message = context.getMessage();
		try {
			message.writeTo(out);
			out.println(""); // just to add a newline
		} catch (Exception e) {
			out.println("Exception in handler: " + e);
		}

	}

}

package cxf.frontend.jaxws.contractfirst.ex1;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogMessageHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {

		boolean isOutboundMessage = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (isOutboundMessage) {
			System.out.println("\n\nOUTBOUND MESSAGE\n");
		} else {
			System.out.println("\n\nINBOUND MESSAGE\n");
		}

		logMessage(context);

		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("\n\nFAULT MESSAGE\n");
		logMessage(context);
		return true;
	}

	@Override
	public void close(MessageContext context) {
	}

	private void logMessage(SOAPMessageContext context) {
		SOAPMessage msg = context.getMessage();

		try {

			Source source = msg.getSOAPPart().getContent();

			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			transformer.transform(source, new StreamResult(System.out));
		} catch (Exception ex) {
			Logger.getLogger(LogMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}

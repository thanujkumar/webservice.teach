package cxf.frontend.jaxws.codefirst.ex2.context;

import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

public class OrderProcessImpl implements OrderProcess {

	@Resource
	WebServiceContext wsc;

	/*
	 * Faults handling:
	 * 
	 * https://docs.oracle.com/cd/E24329_01/web.1211/e24965/faults.htm#WSADV634
	 */

	@Override
	public String processOrder(Order order) {
		//Printing message context info
		findMessageContext(MessageContext.HTTP_REQUEST_HEADERS);
		findMessageContext(MessageContext.HTTP_REQUEST_METHOD);
		findMessageContext(MessageContext.HTTP_RESPONSE_CODE);
		findMessageContext(MessageContext.HTTP_RESPONSE_HEADERS);
		findMessageContext(MessageContext.INBOUND_MESSAGE_ATTACHMENTS);
		findMessageContext(MessageContext.OUTBOUND_MESSAGE_ATTACHMENTS);
		findMessageContext(MessageContext.PATH_INFO);
		findMessageContext(MessageContext.QUERY_STRING);
		findMessageContext(MessageContext.REFERENCE_PARAMETERS);
		findMessageContext(MessageContext.SERVLET_CONTEXT);
		findMessageContext(MessageContext.SERVLET_REQUEST);
		findMessageContext(MessageContext.WSDL_DESCRIPTION);
		findMessageContext(MessageContext.WSDL_INTERFACE);
		findMessageContext(MessageContext.WSDL_OPERATION );
		findMessageContext(MessageContext.WSDL_PORT);
		findMessageContext(MessageContext.WSDL_SERVICE);
	  return process(order);
	}

	private String process(Order order) {
		String custId = order.getCustomerId();
		String itemId = order.getItemId();
		int qty = order.getQuantity();
		if (custId != null && itemId != null && qty != 0) {
			return UUID.randomUUID().toString();
		}

		throw new RuntimeException("Insufficient information to place order, required custId, itemId and qnty");
	}
	
	private void findMessageContext(String key) {
		MessageContext ctx = wsc.getMessageContext();
		try {
		 System.out.println(key +" = "+ ctx.get(key)+", "+ ctx.getScope(key));
		} catch (Exception e ) {
			System.out.println(e.getMessage());
		}
	}
}

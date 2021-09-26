package cxf.frontend.jaxws.messagecontext;

import cxf.frontend.jaxws.codefirst.ex1.InvalidOrderException;
import org.apache.cxf.message.Message;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.UUID;

public class MCOrderProcessImpl implements MCOrderProcess {

    @Resource
    WebServiceContext wsc;

    @Override
    public String processOrder(MCOrder order) throws InvalidOrderException {
        System.out.println("Getting the operation info from the message  context ");
        MessageContext ctx = wsc.getMessageContext();
        QName operation = (QName) ctx.get(Message.WSDL_OPERATION);
        System.out.println("The (Message.WSDL_OPERATION)  name is " + operation);
        System.out.println("The (Message.ENDPOINT_ADDRESS)  name is " + ctx.get(Message.ENDPOINT_ADDRESS));
        System.out.println("The (Message.HTTP_REQUEST_METHOD)  name is " + ctx.get(Message.HTTP_REQUEST_METHOD));
        System.out.println("The (Message.WSDL_SERVICE)  name is " + ctx.get(Message.WSDL_SERVICE));
        System.out.println("The (Message.PATH_INFO)  name is " + ctx.get(Message.PATH_INFO));
        System.out.println("The (Message.CONNECTION_TIMEOUT)  name is " + ctx.get(Message.CONNECTION_TIMEOUT));
        System.out.println("The (Message.PROTOCOL_HEADERS)  name is " + ctx.get(Message.PROTOCOL_HEADERS));
        System.out.println("The (Message.CONTENT_TYPE)  name is " + ctx.get(Message.CONTENT_TYPE));


        System.out.println("Received request by custId=" + order.getCustomerId() + " for itemId=" + order.getItemId() + " with quantity " + order.getQuantity());

        return UUID.randomUUID().toString();
    }
}

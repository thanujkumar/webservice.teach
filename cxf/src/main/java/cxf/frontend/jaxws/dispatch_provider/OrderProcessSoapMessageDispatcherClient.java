package cxf.frontend.jaxws.dispatch_provider;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import java.net.URL;

public class OrderProcessSoapMessageDispatcherClient {

	public static void main(String[] args) throws Exception {

		URL wsdlURL = new URL(ServerForSoapMessageProvider.address+"?wsdl");

		MessageFactory factory = MessageFactory.newInstance();

		QName domProvider = new QName("http://order.demo", "OrderProcessService");
		QName portName = new QName("http://order.demo", "OrderProcessPort");

		Service service = Service.create(wsdlURL, domProvider);

		SOAPMessage soapRequest = factory.createMessage();

		QName processOrderQName = new QName("http://order.demo", "invoke");
		// create the element -
		// <http://order.demo/:processOrder></http://order.demo/:processOrder>
		SOAPElement processOrderResponse = soapRequest.getSOAPBody().addChildElement(processOrderQName);
		SOAPElement order = processOrderResponse.addChildElement("arg0");
		order.addChildElement("customerID").addTextNode("Thanuj");
		order.addChildElement("itemID").addTextNode("I001");
		order.addChildElement("price").addTextNode("200.00");
		order.addChildElement("qty").addTextNode("200");

		DOMSource domRequestMsg = new DOMSource(soapRequest.getSOAPPart());
		Dispatch<DOMSource> domMsg = service.createDispatch(portName, DOMSource.class, Mode.MESSAGE);

		DOMSource domResponseMsg = domMsg.invoke(domRequestMsg);

		SOAPMessage soapReq = factory.createMessage();
		soapReq.getSOAPPart().setContent(domRequestMsg);

		System.out.println("Client Request as a DOMSource data in MESSAGE Mode");
		soapReq.writeTo(System.out);
		System.out.println("\n");

		System.out.println("Response from server: " + domResponseMsg.getNode().getLastChild().getTextContent());

	}
}

package cxf.frontend.jaxws.dispatch_provider;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * Provider - javax.xml.transform.Source, javax.xml.soap.SOAPMessage, and javax.activation.DataSource
 * 
 * javax.xml.transform.Source - The Provider implementation works with three types 
 * of source implementation, DOMSource, SAXSource, and StreamSource. 
 */

@WebServiceProvider()
@ServiceMode(Mode.MESSAGE)
public class OrderProcessSourceProvider implements Provider<DOMSource> {

	@Override
	public DOMSource invoke(DOMSource request) {
		DOMSource response = new DOMSource();

		try {
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage soapReq = factory.createMessage();
			soapReq.getSOAPPart().setContent(request);

			System.out.println("Incoming Client Request as a DOMSource data in MESSAGE Mode");
			soapReq.writeTo(System.out);
			System.out.println("\n");

			Node processOrderNode = soapReq.getSOAPBody().getFirstChild();
			// Get arg0 - order element
			Node order = processOrderNode.getChildNodes().item(0);
			// Get list of child nodes associated with order and print it
			NodeList list = order.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				// Get the child nodes and value as per the order xml request.
				System.out.println(list.item(i).getNodeName() + "=" + list.item(i).getFirstChild().getNodeValue());
			}

			SOAPMessage orderResponse = factory.createMessage();
			QName processOrderQName = new QName("http://dispatch_provider.jaxws.frontend.cxf/", "invokeResponse");
			QName responseQName = new QName("http://dispatch_provider.jaxws.frontend.cxf/", "return");
			// create the element -
			// <http://order.demo/:processOrder></http://order.demo/:processOrder>
			SOAPElement processOrderResponse = orderResponse.getSOAPBody().addChildElement(processOrderQName);
			// create the element inside processOrder -
			// <http://order.demo/:return>ORD1234</http://order.demo/:return>
			processOrderResponse.addChildElement(responseQName).addTextNode("ORD1234-ABCD-12345-2345");

			System.out.println("\n");
			System.out.println("Outgoing response as a DOMSource data in Message Mode" );
			orderResponse.writeTo(System.out);
			System.out.println("\n");
			
			response.setNode(orderResponse.getSOAPPart());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

}

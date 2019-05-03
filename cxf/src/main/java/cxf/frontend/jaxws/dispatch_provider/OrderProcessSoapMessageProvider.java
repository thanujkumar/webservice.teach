package cxf.frontend.jaxws.dispatch_provider;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.ServiceMode;
import javax.xml.ws.WebServiceProvider;

/*
 * Provider - javax.xml.transform.Source, javax.xml.soap.SOAPMessage, and javax.activation.DataSource
 * 
 * javax.xml.transform.Source - The Provider implementation works with three types 
 * of source implementation, DOMSource, SAXSource, and StreamSource. 
 */
@WebServiceProvider(portName = "OrderProcessPort", 
                    serviceName = "OrderProcessService", 
                    targetNamespace = "http://order.demo", 
                    wsdlLocation = "wsdl/OrderProcess.wsdl")
@ServiceMode(Mode.MESSAGE)
public class OrderProcessSoapMessageProvider implements Provider<SOAPMessage> {

	@Override
	public SOAPMessage invoke(SOAPMessage request) {
		SOAPMessage response = null;
		
		try {
			System.out.println("Incoming request as a SoapMessage");
			request.writeTo(System.out);
			System.out.println("\n");
			
			MessageFactory factory = MessageFactory.newInstance();
			response = factory.createMessage();
			
			QName processOrderQName = new QName("http://order.demo", "processOrderResponse");
			
			// create the element -
			// <http://order.demo/:processOrder></http://order.demo/:processOrder>
			SOAPElement respSoapElem = response.getSOAPBody().addChildElement(processOrderQName);
			// create the element inside processOrder -
			// <http://order.demo/:return>ORD1234</http://order.demo/:return>
			respSoapElem.addChildElement("return").addTextNode("ORD1234-ABCD-12345-2345");

			System.out.println("\n");
			System.out.println("Outgoing response as a SoapMessage" );
			response.writeTo(System.out);
			System.out.println("\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}

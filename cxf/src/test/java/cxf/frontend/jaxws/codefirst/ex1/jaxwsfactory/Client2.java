package cxf.frontend.jaxws.codefirst.ex1.jaxwsfactory;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import cxf.frontend.jaxws.codefirst.ex1.OrderProcess;
import cxf.frontend.jaxws.codefirst.ex1.Order;

public class Client2 {
	
	private static final QName SERVICE_NAME = new QName("http://ex1.codefirst.jaxws.frontend.cxf/", "OrderProcessService");
	private static final QName PORT_NAME = new QName("http://ex1.codefirst.jaxws.frontend.cxf/", "OrderProcessPort");
	
	private static final String WSDL_LOCATION = "http://localhost:9999/orderProcess?wsdl";
	
	public static void main(String[] args) throws Exception {
		URL wsdlURL = new URL(WSDL_LOCATION);
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		OrderProcess port = service.getPort(PORT_NAME, OrderProcess.class);
		
		Order order = new Order();
		order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
		System.out.println(port.processOrder(order));
	}

}

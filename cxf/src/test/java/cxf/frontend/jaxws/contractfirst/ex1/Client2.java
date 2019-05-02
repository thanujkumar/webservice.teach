package cxf.frontend.jaxws.contractfirst.ex1;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import demo.order.Order;
import demo.order.OrderProcess;



public class Client2 {
	
	private static final QName SERVICE_NAME = new QName("http://order.demo", "OrderProcessService");
	private static final QName PORT_NAME = new QName("http://order.demo", "OrderProcessPort");
	
	private static final String WSDL_LOCATION = "http://localhost:9999/cxfws/ex1/OrderProcess3?wsdl";
	
	public static void main(String[] args) throws Exception {
		URL wsdlURL = new URL(WSDL_LOCATION);
		Service service = Service.create(wsdlURL, SERVICE_NAME);
		OrderProcess port = service.getPort(PORT_NAME, OrderProcess.class);
		
		Order order = new Order();
		order.setCustomerID("123A");
		order.setItemID("SomeItem");
		order.setPrice(2.3);
		order.setQty(10);
		System.out.println(port.processOrder(order));
	}

}

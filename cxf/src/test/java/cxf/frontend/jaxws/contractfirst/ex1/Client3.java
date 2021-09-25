package cxf.frontend.jaxws.contractfirst.ex1;

import java.net.URL;

import demo.order.Order;
import demo.order.OrderProcess;
import demo.order.OrderProcessService;

//Based on generated client code from WSDL
//// Run maven jetty to execute client
public class Client3 {

	private static final String WSDL_LOCATION = "http://localhost:9999/cxfws/ex1/OrderProcess3?wsdl";
	
	
	public static void main(String[] args) throws Exception {
		OrderProcessService service = new OrderProcessService(new URL(WSDL_LOCATION));
		OrderProcess port = service.getOrderProcessPort();
		
		Order order = new Order();
		order.setCustomerID("123A");
		order.setItemID("SomeItem");
		order.setPrice(2.3);
		order.setQty(10);
		System.out.println(port.processOrder(order));
	}
}

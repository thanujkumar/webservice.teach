package cxf.frontend.jaxws.codefirst.ex2.jaxwsfactory;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import cxf.frontend.jaxws.codefirst.ex1.OrderProcess;
import cxf.transport.local.Order;

public class Client {
 
	//Run cxf.frontend.jaxws.codefirst.ex2.jaxwsfactory.UsingJaxWSServerFactory
	public static void main(String[] args) throws Exception {
		
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean(); //based on ClientProxyFactoryBean
		proxyFactory.getFeatures().add(new LoggingFeature());
		proxyFactory.setAddress("http://localhost:9999/orderProcess");
		OrderProcess processOrder = proxyFactory.create(OrderProcess.class);
		
		Order order = new Order();
		order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
		System.out.println(processOrder.processOrder(order));
	}
}

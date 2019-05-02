package cxf.frontend.jaxws.contractfirst.ex1;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import demo.order.Order;
import demo.order.OrderProcess;


public class Client {
 
	//Run cxf.frontend.jaxws.codefirst.ex2.jaxwsfactory.UsingJaxWSServerFactory
	public static void main(String[] args) throws Exception {
		
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean(); //based on ClientProxyFactoryBean
		proxyFactory.getFeatures().add(new LoggingFeature());
		proxyFactory.setAddress("http://localhost:9999/cxfws/ex1/OrderProcess3");
		OrderProcess processOrder = proxyFactory.create(OrderProcess.class);
		
		Order order = new Order();
		order.setCustomerID("123A");
		order.setItemID("SomeItem");
		order.setPrice(2.3);
		order.setQty(10);
		System.out.println(processOrder.processOrder(order));
	}
}

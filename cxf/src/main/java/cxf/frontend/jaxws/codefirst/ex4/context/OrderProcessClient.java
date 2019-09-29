package cxf.frontend.jaxws.codefirst.ex4.context;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import cxf.transport.in_vm.Order;
import cxf.transport.in_vm.OrderProcess;

public class OrderProcessClient {

	public static void main(String[] args) throws Exception {
		
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean(); //based on ClientProxyFactoryBean
		proxyFactory.getFeatures().add(new LoggingFeature());
		proxyFactory.setAddress("http://localhost:9292/orderProcess4");
		OrderProcess processOrder = proxyFactory.create(OrderProcess.class);
		
		Order order = new Order();
		order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
		System.out.println(processOrder.processOrder(order));

		
	}
}

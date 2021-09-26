package cxf.frontend.simplefrontend;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {
 
	//Run cxf.frontend.jaxws.codefirst.ex2.jaxwsfactory.UsingJaxWSServerFactory
	public static void main(String[] args) throws Exception {

		ClientProxyFactoryBean proxyFactory = new ClientProxyFactoryBean (); //based on ClientProxyFactoryBean
		proxyFactory.getFeatures().add(new LoggingFeature());
		proxyFactory.setAddress("http://localhost:9999/orderProcess");
		MyOrderProcess processOrder = proxyFactory.create(MyOrderProcess.class);
		
		MyOrder order = new MyOrder();
		order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
		System.out.println(processOrder.processMyOrder(order));
	}
}

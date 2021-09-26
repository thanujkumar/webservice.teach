package cxf.frontend.jaxws.interceptors;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


public class IncptClient {

	public static void main(String[] args) throws Exception {

		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean(); //based on ClientProxyFactoryBean
		proxyFactory.getFeatures().add(new LoggingFeature());
		proxyFactory.getOutInterceptors().add(new MyClientOutInterceptor()); //Same for out from client
		proxyFactory.getInInterceptors().add(new MyClientInInterceptor());//Same for in to client
		proxyFactory.setAddress("http://localhost:9999/orderProcess");
		IncptOrderProcess processOrder = proxyFactory.create(IncptOrderProcess.class);

		IncptOrder order = new IncptOrder();
		order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
		System.out.println(processOrder.processOrder(order));


	}

}

package cxf.transport.local;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

//http://ashakirin.blogspot.com/2012/02/custom-cxf-transport.html

public class InVMOrderProcessClient {
 
	public static void main(String[] args) throws Exception {
	
		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean(); //based on ClientProxyFactoryBean
		proxyFactory.getFeatures().add(new LoggingFeature());
		proxyFactory.setAddress("local://OrderProcessLocal");
		OrderProcess processOrder = proxyFactory.create(OrderProcess.class);
		
		Order order = new Order();
		order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
		System.out.println(processOrder.processOrder(order));
	}
}

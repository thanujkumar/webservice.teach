package cxf.transport.in_vm;

import java.util.Map;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.extension.ExtensionManagerImpl;
import org.apache.cxf.configuration.ConfiguredBeanLocator;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

//http://ashakirin.blogspot.com/2012/02/custom-cxf-transport.html

public class InVMOrderProcessClient {
 
	public static void runInAsVMClient() throws Exception {
	
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
		
		Bus clientBus = BusFactory.getDefaultBus();
		for (Feature f : clientBus.getFeatures()) {
			 System.out.println(f);
		}
		Map<String, Object> props = clientBus.getProperties();
		for(String s : props.keySet()) {
			System.out.println(s +" -> "+ props.get(s));
		}
		for (Interceptor<?> m : clientBus.getInInterceptors()) {
			 System.out.println ("IN -> "+ m);
		}
		for (Interceptor<?> m : clientBus.getOutInterceptors()) {
			 System.out.println ("OUT -> "+ m);
		}
		for (Interceptor<?> m : clientBus.getInFaultInterceptors()) {
			 System.out.println ("INF -> "+ m);
		}
		for (Interceptor<?> m : clientBus.getOutFaultInterceptors()) {
			 System.out.println ("OUTF -> "+ m);
		}

		ExtensionManagerImpl extM = (ExtensionManagerImpl) clientBus.getExtension(ConfiguredBeanLocator.class);
		
	}
}

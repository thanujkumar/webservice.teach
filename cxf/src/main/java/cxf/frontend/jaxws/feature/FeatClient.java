package cxf.frontend.jaxws.feature;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.common.gzip.GZIPFeature;


public class FeatClient {

	public static void main(String[] args) throws Exception {


		JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean(); //based on ClientProxyFactoryBean
		proxyFactory.getFeatures().add(new LoggingFeature());
		proxyFactory.getFeatures().add(new GZIPFeature());
		proxyFactory.getFeatures().add(new FeatLogPhaseFeature());
		proxyFactory.setAddress("http://localhost:9999/orderProcess"); //using TunnelJ IntelliJ TCP monitoring redirects to 9999
		FeatOrderProcess processOrder = proxyFactory.create(FeatOrderProcess.class);

		FeatOrder order = new FeatOrder();
		order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
		System.out.println(processOrder.processOrder(order));


	}

}

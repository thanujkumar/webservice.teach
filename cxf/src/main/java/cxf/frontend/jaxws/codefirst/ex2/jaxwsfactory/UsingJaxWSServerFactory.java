package cxf.frontend.jaxws.codefirst.ex2.jaxwsfactory;

import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import cxf.frontend.jaxws.codefirst.ex1.OrderProcess;
import cxf.frontend.jaxws.codefirst.ex1.OrderProcessImpl;

public class UsingJaxWSServerFactory {

	public static void main(String[] args) {
		//CXF front-ends like JAX-WS use the service model to create web services - service model represent the service (starts at ServiceInfo)
		//CXF supports two types of data binding components—JAXB and Aegis
		
		OrderProcessImpl orderProcessImpl = new OrderProcessImpl();
		JaxWsServerFactoryBean jaxwsFactory = new JaxWsServerFactoryBean(); // based on ServerFactoryBean
		jaxwsFactory.setServiceClass(OrderProcess.class);
		jaxwsFactory.setAddress("http://localhost:9999/orderProcess");
		jaxwsFactory.setServiceBean(orderProcessImpl);
		LoggingFeature logging = new LoggingFeature();
		logging.setPrettyLogging(true);
		jaxwsFactory.getFeatures().add(logging);
		jaxwsFactory.create();
		System.out.println("Server started .....");
	}
}

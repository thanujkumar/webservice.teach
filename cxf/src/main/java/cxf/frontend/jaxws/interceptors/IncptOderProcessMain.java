package cxf.frontend.jaxws.interceptors;


import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class IncptOderProcessMain {

	public static void main(String[] args) {

		IncptOrderProcessImpl orderProcessImpl = new 	IncptOrderProcessImpl();
		JaxWsServerFactoryBean jaxwsFactory = new JaxWsServerFactoryBean(); // based on ServerFactoryBean
		jaxwsFactory.getOutInterceptors().add(new MyServerOutInterceptor()); //Same for out from server,  also can be added to @WebService as @OutInterceptor
		jaxwsFactory.getInInterceptors().add(new MyServerInInterceptor());//Same for in to server, also can be added to @WebService as @InInterceptor
		jaxwsFactory.setServiceClass(	IncptOrderProcess.class);
		jaxwsFactory.setAddress("http://localhost:9999/orderProcess");
		jaxwsFactory.setServiceBean(orderProcessImpl);
		LoggingFeature logging = new LoggingFeature();
		logging.setPrettyLogging(true);
		jaxwsFactory.getFeatures().add(logging);
		Server server = jaxwsFactory.create();
		Bus bus = jaxwsFactory.getBus();
		System.out.println("Server started ....." + bus);
	}
}

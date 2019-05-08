package cxf.transport.local;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.managers.DestinationFactoryManagerImpl;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.DestinationFactoryManager;
import org.apache.cxf.transport.local.LocalTransportFactory;

public class InVMOrderProcessServer {

	public static void main(String[] args) throws Exception {
//		Bus bus = BusFactory.getDefaultBus();
//		LocalTransportFactory localTransport = new LocalTransportFactory();
//		DestinationFactoryManager dfm = new DestinationFactoryManagerImpl(bus);
//		
//		dfm.registerDestinationFactory("http://schemas.xmlsoap.org/soap/http", localTransport);
//		dfm.registerDestinationFactory("http://schemas.xmlsoap.org/wsdl/soap/http", localTransport);
//		dfm.registerDestinationFactory("http://cxf.apache.org/bindings/xformat", localTransport);
//		dfm.registerDestinationFactory("http://cxf.apache.org/transports/local", localTransport);
//		 
//		ConduitInitiatorManager extension = bus.getExtension(ConduitInitiatorManager.class);
//		extension.registerConduitInitiator("http://cxf.apache.org/transports/local", localTransport);
//		extension.registerConduitInitiator("http://schemas.xmlsoap.org/wsdl/soap/http", localTransport);
//		extension.registerConduitInitiator("http://schemas.xmlsoap.org/soap/http", localTransport);
//		extension.registerConduitInitiator("http://cxf.apache.org/bindings/xformat", localTransport);
		
		OrderProcessImpl orderProcessImpl = new OrderProcessImpl();
		JaxWsServerFactoryBean jaxwsFactory = new JaxWsServerFactoryBean(); // based on ServerFactoryBean
		jaxwsFactory.setServiceClass(OrderProcess.class);
		jaxwsFactory.setAddress("local://OrderProcessLocal");
		jaxwsFactory.setServiceBean(orderProcessImpl);
		LoggingFeature logging = new LoggingFeature();
		logging.setPrettyLogging(true);
		jaxwsFactory.getFeatures().add(logging);
		jaxwsFactory.create();
		System.out.println("Server started .....");

		     //---------OR---------------//
		
//		org.apache.cxf.jaxws.EndpointImpl endpoint = (EndpointImpl) Endpoint.publish("local://OrderProcessLocal",
//				orderProcessImpl);
		
		   //---------OR---------------//
		
//		ServerFactoryBean sf = new ServerFactoryBean();
//		sf.setAddress("local://OrderProcessLocal");
//		sf.setServiceBean(orderProcessImpl);
//		sf.setServiceClass(OrderProcess.class); // Optionally specify the service interface
//		sf.create();
		
		//Now call the as client in the same VM
		InVMOrderProcessClient.runInAsVMClient();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Stopping server....");
		System.exit(0);
	}

}

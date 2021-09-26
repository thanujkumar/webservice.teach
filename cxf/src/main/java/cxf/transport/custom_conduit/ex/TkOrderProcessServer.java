package cxf.transport.custom_conduit.ex;

import cxf.transport.custom_conduit.CustomTransportFactory;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.DestinationFactoryManager;

//Both Server and client should run in same VM for communication

public class TkOrderProcessServer {

	public static void main(String[] args) throws Exception {
		//org.apache.cxf.phase.Phase
		//http://ashakirin.blogspot.com/2012/02/custom-cxf-transport.html
		//http://cxf.apache.org/docs/transports.html
		CustomTransportFactory customTransportFactory = new CustomTransportFactory();
		Bus bus = BusFactory.getThreadDefaultBus();//getDefaultBus(true);
		DestinationFactoryManager dfm = bus.getExtension(DestinationFactoryManager.class);
		dfm.registerDestinationFactory(CustomTransportFactory.TRANSPORT_ID, customTransportFactory);
		ConduitInitiatorManager extension = bus.getExtension(ConduitInitiatorManager.class);
		extension.registerConduitInitiator(CustomTransportFactory.TRANSPORT_ID, customTransportFactory);

		TkOrderProcessImpl orderProcessImpl = new TkOrderProcessImpl();
		JaxWsServerFactoryBean jaxwsFactory = new JaxWsServerFactoryBean(); // based on ServerFactoryBean
		jaxwsFactory.setServiceClass(TkOrderProcess.class);
		jaxwsFactory.setAddress("tk://OrderProcessLocal");
		jaxwsFactory.setServiceBean(orderProcessImpl);
		LoggingFeature logging = new LoggingFeature();
		logging.setPrettyLogging(true);
		jaxwsFactory.getFeatures().add(logging);
		jaxwsFactory.create();
		System.out.println("Server started .....");
	}

}

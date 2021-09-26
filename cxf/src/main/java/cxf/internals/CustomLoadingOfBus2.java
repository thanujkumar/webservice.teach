package cxf.internals;

import cxf.frontend.jaxws.codefirst.ex1.OrderProcess;
import cxf.frontend.jaxws.codefirst.ex1.OrderProcessImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.managers.DestinationFactoryManagerImpl;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import java.util.Collection;

public class CustomLoadingOfBus2 {
    //cxf/testutils/src/main/java/org/apache/cxf/testutil/common/

    public static void main(String[] args) {
        Bus b = BusFactory.getDefaultBus(); //ExtensionManagerBus, SpringBus, BlueprintBus
        Server server = null;
        //Need Servlet API

        OrderProcessImpl orderProcessImpl = new OrderProcessImpl();
        JaxWsServerFactoryBean jaxwsFactory = new JaxWsServerFactoryBean(); // based on ServerFactoryBean
        jaxwsFactory.setServiceClass(OrderProcess.class);
        jaxwsFactory.setAddress("http://localhost:9999/orderProcess");
        jaxwsFactory.setServiceBean(orderProcessImpl);
        LoggingFeature logging = new LoggingFeature();
        logging.setPrettyLogging(true);
        jaxwsFactory.getFeatures().add(logging);

        jaxwsFactory.setBus(b);
        server = jaxwsFactory.create();

        BusFactory.setDefaultBus(b); //BusFactory -> SpringBusFactory and CXFBusFactory

        server.start();
        System.out.println(server + "....Started...." + b); //Not SpringBus, see below as it needs to be loaded from Servlet


        /*
        Bus is the backbone of the CXF architecture. The CXF bus is comprised of a
        Spring-based configuration file, namely, cxf.xml which is loaded upon servlet
        initialization through SpringBusFactory. It defines a common context for all
        the endpoints. It wires all the runtime infrastructure components and provides a
        common application context. The SpringBusFactory scans and loads the relevant
        configuration files in the META-INF/cxf directory placed in the classpath and
        accordingly builds the application context. It builds the application context from
        the following files:
        • META-INF/cxf/cxf.xml
        • META-INF/cxf/cxf-extension.xml
        • META-INF/cxf/cxf-property-editors.xml
         */

        Collection<Feature> features = b.getFeatures(); //If set any by code or if exits default
        features.stream().forEach(System.out::println);

        Collection<Feature> features2 = jaxwsFactory.getFeatures(); //If set any by code or if exits default
        features2.stream().forEach(System.out::println);

        Collection<Feature> features3 = jaxwsFactory.getBus().getFeatures(); //If set any by code or if exits default
        features3.stream().forEach(System.out::println);

    }
}

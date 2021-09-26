package cxf.frontend.jaxws.feature;


import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.common.gzip.GZIPFeature;


public class FeatOderProcessMain {

    public static void main(String[] args) {

        FeatOrderProcessImpl orderProcessImpl = new FeatOrderProcessImpl();
        JaxWsServerFactoryBean jaxwsFactory = new JaxWsServerFactoryBean(); // based on ServerFactoryBean
        jaxwsFactory.getFeatures().add(new FeatLogPhaseFeature());
        jaxwsFactory.getFeatures().add(new GZIPFeature());
        jaxwsFactory.setServiceClass(FeatOrderProcess.class);
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

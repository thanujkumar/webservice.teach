package cxf.frontend.jaxws.messagecontext;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class MCOrderProcessMain {
    public static void main(String[] args) {
        MCOrderProcessImpl orderProcessImpl = new MCOrderProcessImpl();

        JaxWsServerFactoryBean jaxwsFactory = new JaxWsServerFactoryBean(); // based on ServerFactoryBean
        jaxwsFactory.setServiceClass(MCOrderProcess.class);
        jaxwsFactory.setAddress("http://localhost:9999/mcOrderProcess");
        jaxwsFactory.setServiceBean(orderProcessImpl);
        LoggingFeature logging = new LoggingFeature();
        logging.setPrettyLogging(true);
        jaxwsFactory.getFeatures().add(logging);
        Server server = jaxwsFactory.create();
        Bus bus = jaxwsFactory.getBus();
        System.out.println("Server started ....." + bus);
    }
}

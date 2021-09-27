package cxf.frontend.jaxrs.ex1;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

import java.util.List;

public class HelloServiceMain {
    public static void main(String[] args) {
        JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
        factoryBean.getFeatures().add(new MsgLoggingFeature());

        List providers = factoryBean.getProviders();
        providers.add(new JacksonJsonProvider());

        //Slf4jEventSender
        LoggingFeature logging = new LoggingFeature();
        logging.setPrettyLogging(true);
        factoryBean.getFeatures().add(logging);
        factoryBean.setResourceClasses(HelloServiceImpl.class);
        factoryBean.setServiceBean(new HelloServiceImpl());
        factoryBean.setAddress("http://localhost:9999");
        factoryBean.create();
        System.out.println("Started....." + factoryBean.toString());
    }
}

package cxf.frontend.jaxws.codefirst.ex4.jaxws;


import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.frontend.ClientProxyFactoryBean;

public class HelloWordJaxWsClient {

    //TODO not working as args request has namespace - <arg0 xmlns="http://jaxws.ex4.codefirst.jaxws.frontend.cxf/">thanu</arg0>
    public static void main(String[] args) throws Exception {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(HelloWorldJaxWs.class);
        factory.setAddress(HelloWorldJaxWsServer.endpoint);
        factory.getFeatures().add(new LoggingFeature());
       //HelloWorldJaxWs helloWorldJaxWsEndpoint = factory.create(HelloWorldJaxWs.class);
        HelloWorldJaxWs helloWorldJaxWsEndpoint = (HelloWorldJaxWs) factory.create();
        System.out.println(helloWorldJaxWsEndpoint.greetings(System.getProperty("user.name")));
    }

}


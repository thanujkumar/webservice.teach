package cxf.frontend.simplefrontend;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.frontend.ServerFactoryBean;

/*
Apart from JAX-WS frontend, CXF also supports what is known as 'simple frontend'.
The simple frontend provides simple components or Java classes that use reflection
to build and publish web services. It is simple because we do not use any annotation
to create web services.
 */
public class SimpleFrontEndMain {
    //needs simple-frontend jar
    public static void main(String[] args) {
        MyOrderProcessImpl orderProcessImpl = new MyOrderProcessImpl();
        ServerFactoryBean svrFactory = new ServerFactoryBean();
        svrFactory.setServiceClass(MyOrderProcess.class);
        svrFactory.setAddress("http://localhost:9999/orderProcess");
        svrFactory.setServiceBean(orderProcessImpl);
        Server server = svrFactory.create();
        server.start();
        Bus bus = svrFactory.getBus();
        System.out.println(bus+" ....started....");
    }
}

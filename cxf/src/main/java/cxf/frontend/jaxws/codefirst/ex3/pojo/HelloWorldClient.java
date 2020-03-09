package cxf.frontend.jaxws.codefirst.ex3.pojo;

import org.apache.cxf.frontend.ClientProxyFactoryBean;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setAddress("http://localhost:5000/Hello");
        HelloWorld helloServer = factory.create(HelloWorld.class);
        System.out.println(helloServer.greetings(System.getProperty("user.name")));
    }
}
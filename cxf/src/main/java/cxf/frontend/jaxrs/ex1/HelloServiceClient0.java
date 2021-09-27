package cxf.frontend.jaxrs.ex1;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class HelloServiceClient0 {
    public static void main(String[] args) {
        HelloService helloService = JAXRSClientFactory.create("http://localhost:9999", HelloService.class);
        System.out.println(helloService.sayHello1());
        System.out.println(helloService.sayHello2());
        System.out.println(helloService.sayHello3());
        System.out.println(helloService.sayHello4().readEntity(String.class));
    }
}

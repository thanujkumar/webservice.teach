package cxf.frontend.jaxws.codefirst.ex4.jaxws;


import org.apache.cxf.ext.logging.LoggingFeature;

import javax.xml.ws.Endpoint;

public class HelloWorldJaxWsServer {

    static  final  String endpoint = "http://localhost:5000/HelloServerPort"; //soap address

    public static void main(String[] args) {
        HelloWorldJaxWs implemtation = new HelloWorldJaxWsImpl();
        Endpoint.publish(endpoint, implemtation, new LoggingFeature());
        System.out.println("Server ready @-"+ endpoint);
    }
}

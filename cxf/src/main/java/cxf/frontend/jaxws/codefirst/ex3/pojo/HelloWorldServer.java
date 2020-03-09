package cxf.frontend.jaxws.codefirst.ex3.pojo;

import org.apache.cxf.frontend.ServerFactoryBean;

//Using cxf ServerFactoryBean - https://www.tutorialspoint.com/apache_cxf/apache_cxf_with_pojo.htm
public class HelloWorldServer {

    static final String url = "http://localhost:5000/Hello";

    protected HelloWorldServer() throws Exception {
        ServerFactoryBean factory = new ServerFactoryBean();
        factory.setServiceClass(HelloWorld.class);
        factory.setAddress(url);
        factory.setServiceBean(new HelloWorldImpl());
        factory.create();
    }
    public static void main(String[] args) throws Exception {
        new HelloWorldServer();
        System.out.println("Listening on port 5000 ... : " + url);
    }
}

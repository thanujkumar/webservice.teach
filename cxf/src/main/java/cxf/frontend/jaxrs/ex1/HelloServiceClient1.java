package cxf.frontend.jaxrs.ex1;

import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelloServiceClient1 {
    public static void main(String[] args) {
        WebClient client = WebClient.create("http://localhost:9999/");
        client.path("/hello1").type(MediaType.TEXT_PLAIN);
        Response response = client.get();
        System.out.println(response.readEntity(String.class));
    }
}

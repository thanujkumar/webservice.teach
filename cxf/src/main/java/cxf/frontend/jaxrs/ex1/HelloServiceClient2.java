package cxf.frontend.jaxrs.ex1;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class HelloServiceClient2 {
    public static void main(String[] args) {
        ClientBuilder clientBuilder=  ClientBuilder.newBuilder();
        Client client = clientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:9999");
        Invocation.Builder builder = webTarget.path("/hello1").request(MediaType.TEXT_PLAIN);
        Response response = builder.get();
        System.out.println(response.readEntity(String.class));
    }
}

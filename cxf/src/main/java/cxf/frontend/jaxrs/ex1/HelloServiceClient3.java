package cxf.frontend.jaxrs.ex1;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

public class HelloServiceClient3 {
    public static void main(String[] args) {
        ArrayList providers = new ArrayList();
        providers.add(new JacksonJsonProvider());

        providers.add(new LoggingFeature());
        WebClient client = WebClient.create("http://localhost:9999/", providers);
        Greeting service  = client.path("/hello3").accept(MediaType.APPLICATION_JSON)
                .type(MediaType.TEXT_PLAIN).get(Greeting.class);
        System.out.println(service.getGreeting());
    }
}

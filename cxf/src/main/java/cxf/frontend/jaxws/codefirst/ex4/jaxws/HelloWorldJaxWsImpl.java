package cxf.frontend.jaxws.codefirst.ex4.jaxws;

import javax.jws.WebService;

@WebService(endpointInterface = "cxf.frontend.jaxws.codefirst.ex4.jaxws.HelloWorldJaxWs")
public class HelloWorldJaxWsImpl implements HelloWorldJaxWs {
    @Override
    public String greetings(String text) {
        return "Hi "+text;
    }
}

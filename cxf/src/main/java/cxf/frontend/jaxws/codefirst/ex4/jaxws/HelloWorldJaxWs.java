package cxf.frontend.jaxws.codefirst.ex4.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HelloWorldJaxWs {

    @WebMethod
    String greetings(String text);

}

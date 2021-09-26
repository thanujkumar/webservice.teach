package cxf.frontend.jaxws.messagecontext;

import cxf.frontend.jaxws.codefirst.ex1.InvalidOrderException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MCOrderProcess {

    @WebMethod //optional, can be used for customization
    String processOrder(MCOrder order) throws InvalidOrderException;
}

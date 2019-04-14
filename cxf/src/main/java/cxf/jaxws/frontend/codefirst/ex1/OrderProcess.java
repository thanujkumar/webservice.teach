package cxf.jaxws.frontend.codefirst.ex1;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Create a Service Endpoint Interface (SEI) and define a business method to be
 * used with the web service.
 */
@WebService
public interface OrderProcess {

	@WebMethod
	String processOrder(Order order) throws InvalidOrderException;
}

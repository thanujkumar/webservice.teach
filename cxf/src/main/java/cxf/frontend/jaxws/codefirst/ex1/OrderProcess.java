package cxf.frontend.jaxws.codefirst.ex1;

import javax.jws.WebMethod;
import javax.jws.WebService;

import cxf.transport.in_vm.Order;

/**
 * Create a Service Endpoint Interface (SEI) and define a business method to be
 * used with the web service.
 */
@WebService
public interface OrderProcess {

	@WebMethod //optional, can be used for customization
	String processOrder(Order order) throws InvalidOrderException;
}

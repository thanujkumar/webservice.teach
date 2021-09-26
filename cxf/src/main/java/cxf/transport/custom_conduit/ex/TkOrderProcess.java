package cxf.transport.custom_conduit.ex;

import cxf.transport.in_vm.InvalidOrderException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 * Create a Service Endpoint Interface (SEI) and define a business method to be
 * used with the web service.
 */
@WebService
public interface TkOrderProcess {

	@WebMethod //optional, can be used for customization
	String processOrder(@WebParam(name="order") TkOrder order) throws InvalidOrderException;
}

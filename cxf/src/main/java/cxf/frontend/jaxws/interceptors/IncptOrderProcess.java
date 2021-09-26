package cxf.frontend.jaxws.interceptors;

import cxf.frontend.jaxws.codefirst.ex1.InvalidOrderException;
import org.apache.cxf.interceptor.InInterceptors;

import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * Create a Service Endpoint Interface (SEI) and define a business method to be
 * used with the web service.
 */
@WebService
@InInterceptors(interceptors = {"cxf.frontend.jaxws.interceptors.MyServerInInterceptor"}) //full package name required
public interface IncptOrderProcess {

	@WebMethod //optional, can be used for customization
	String processOrder(IncptOrder order) throws InvalidOrderException;
}

package cxf.frontend.jaxws.feature;

import cxf.frontend.jaxws.codefirst.ex1.InvalidOrderException;
import cxf.frontend.jaxws.interceptors.IncptOrder;
import org.apache.cxf.interceptor.InInterceptors;

import javax.jws.WebMethod;
import javax.jws.WebService;


/**
 * Create a Service Endpoint Interface (SEI) and define a business method to be
 * used with the web service.
 */
@WebService
public interface FeatOrderProcess {

	@WebMethod //optional, can be used for customization
	String processOrder(FeatOrder order) throws InvalidOrderException;
}

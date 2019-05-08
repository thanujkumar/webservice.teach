package cxf.frontend.jaxws.codefirst.ex4.context;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Create a Service Endpoint Interface (SEI) and define a business method to be
 * used with the web service.
 */
@WebService(serviceName="OrderProcessService4", portName="OrderProcessPort4")
public interface OrderProcess {

	@WebMethod //optional, can be used for customization
	String processOrder(@WebParam(name="order", mode = WebParam.Mode.IN) Order order) ;
}

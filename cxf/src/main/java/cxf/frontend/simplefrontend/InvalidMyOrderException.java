package cxf.frontend.simplefrontend;

import javax.xml.ws.WebFault;

/*
 * This class evolved to show different examples
 * No @WebFault annotation and No MyFaultBean
 * Only @WebFault annotation
 * Only @WebFault(name="InvalidOrder") annotation
 * Only @WebFault(name="InvalidOrder") annotation with MyFaultBean to provide custom "<details>"
 * 
 */
@WebFault(name="InvalidOrder")
public class InvalidMyOrderException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = -6080930984280808986L;

	private FaultBean faultInfo;

	public InvalidMyOrderException(String message, Throwable t) {
		super(message, t);
	}

	public InvalidMyOrderException(String message, FaultBean falutBean) {
		super(message);
		this.faultInfo = falutBean;
	}
	
	//Method required to provide custom fault <detail> element info as per jaxws specification
	public FaultBean getFaultInfo() {
		return faultInfo;
	}
}

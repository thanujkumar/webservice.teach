package cxf.transport.in_vm;

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
public class InvalidOrderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6080930984280808986L;

	private MyFaultBean faultInfo;
	
	public InvalidOrderException(String message, Throwable t) {
		super(message, t);
	}

	public InvalidOrderException(String message, MyFaultBean falutBean) {
		super(message);
		this.faultInfo = falutBean;
	}
	
	//Method required to provide custom fault <detail> element info
	public MyFaultBean getFaultInfo() {
		return faultInfo;
	}
}

package cxf.frontend.jaxws.contractfirst.ex1;

import java.util.UUID;

import cxf.frontend.jaxws.codefirst.ex1.MyFaultBean;
import demo.order.Order;
import demo.order.OrderProcess;

//In this wsdl is created and code generated - check resources/wsdl/OrderProcess.wsdl
//all configuration exists in webapp/WEB-INF/cxfbeans.xml
//run mvn jetty:run from cxf folder
//Package name of generated code with be as per wsdl:definition targetNamespace
//A Java interface mapped from a wsdl:portType is called a Service Endpoint Interface or SEI for short (In our case OrderProcess is the name given to wsdl:portType, so the interface name is generated).
public class OrderProcessImplWs implements OrderProcess/**interface name Maps  wsdl:portType Name**/ {

	//below method name maps to wsdl:operation inside wsdl:binding
	@Override
	public String processOrder(Order order) {
		String custId = order.getCustomerID();
		String itemId = order.getItemID();
		int qty = order.getQty();
		if (custId != null && itemId != null && qty != 0) {
			return UUID.randomUUID().toString();
		}
		MyFaultBean faultBean = new MyFaultBean();
		faultBean.setFaultCode("Client");
		faultBean.setFaultString("Invalid request");
		faultBean.setFaultCodeURL("http://demo.order/faultcodes");
		throw new OrderException("Insufficient information to place order, required custId, itemId and qnty",
				faultBean);
	}

	class OrderException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1440508251035131785L;
		private MyFaultBean faultInfo;

		public OrderException(String message, Throwable t) {
			super(message, t);
		}

		public OrderException(String message, MyFaultBean falutBean) {
			super(message);
			this.faultInfo = falutBean;
		}

		// Method required to provide custom fault <detail> element info
		public MyFaultBean getFaultInfo() {
			return faultInfo;
		}
	}

}

package cxf.transport.in_vm;

import java.util.UUID;

public class OrderProcessImpl implements OrderProcess {
	
	/*
	 * Faults handling:
	 * 
	 * https://docs.oracle.com/cd/E24329_01/web.1211/e24965/faults.htm#WSADV634
	 */

	@Override
	public String processOrder(Order order) throws InvalidOrderException{
		return process(order);
	}

	private String process(Order order) throws InvalidOrderException {
		String custId = order.getCustomerId();
		String itemId = order.getItemId();
		int qty = order.getQuantity();
		if (custId != null && itemId != null && qty != 0) {
			return UUID.randomUUID().toString();
		}
		MyFaultBean faultBean = new MyFaultBean();
		faultBean.setFaultCode("Client");
		faultBean.setFaultString("Invalid request");
		faultBean.setFaultCodeURL("http://test.com/faultcodes");
		throw new InvalidOrderException("Insufficient information to place order, required custId, itemId and qnty", faultBean);
	}
}

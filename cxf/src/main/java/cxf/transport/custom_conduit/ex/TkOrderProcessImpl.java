package cxf.transport.custom_conduit.ex;

import cxf.transport.in_vm.InvalidOrderException;

import java.util.UUID;

public class TkOrderProcessImpl implements TkOrderProcess {
	
	/*
	 * Faults handling:
	 * 
	 * https://docs.oracle.com/cd/E24329_01/web.1211/e24965/faults.htm#WSADV634
	 */

	@Override
	public String processOrder(TkOrder order) throws InvalidOrderException {
		return process(order);
	}

	private String process(TkOrder order) throws InvalidOrderException {
		String custId = order.getCustomerId();
		String itemId = order.getItemId();
		int qty = order.getQuantity();
		System.out.println("Received request by custId=" + order.getCustomerId() + " for itemId=" + order.getItemId() + " with quantity " + order.getQuantity());
		return UUID.randomUUID().toString();
	}
}

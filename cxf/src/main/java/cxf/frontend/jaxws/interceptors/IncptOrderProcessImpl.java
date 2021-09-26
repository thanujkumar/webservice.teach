package cxf.frontend.jaxws.interceptors;

import cxf.frontend.jaxws.codefirst.ex1.InvalidOrderException;
import cxf.frontend.jaxws.codefirst.ex1.MyFaultBean;

import java.util.UUID;


public class IncptOrderProcessImpl implements IncptOrderProcess {

    @Override
    public String processOrder(IncptOrder order) throws InvalidOrderException {
        return process(order);
    }

    private String process(IncptOrder order) throws InvalidOrderException {
        String custId = order.getCustomerId();
        String itemId = order.getItemId();
        int qty = order.getQuantity();
        System.out.println("Received request by custId=" + order.getCustomerId() + " for itemId=" + order.getItemId() + " with quantity " + order.getQuantity());
        return UUID.randomUUID().toString();

    }
}

package cxf.frontend.jaxws.feature;

import cxf.frontend.jaxws.codefirst.ex1.InvalidOrderException;
import cxf.frontend.jaxws.interceptors.IncptOrder;

import java.util.UUID;


public class FeatOrderProcessImpl implements FeatOrderProcess {

    @Override
    public String processOrder(FeatOrder order) throws InvalidOrderException {
        return process(order);
    }

    private String process(FeatOrder order) throws InvalidOrderException {
        String custId = order.getCustomerId();
        String itemId = order.getItemId();
        int qty = order.getQuantity();
        System.out.println("Received request by custId=" + order.getCustomerId() + " for itemId=" + order.getItemId() + " with quantity " + order.getQuantity());
        return UUID.randomUUID().toString();

    }
}

package cxf.frontend.simplefrontend;

import java.util.UUID;

public class MyOrderProcessImpl implements MyOrderProcess {

    @Override
    public String processMyOrder(MyOrder order) throws InvalidMyOrderException {
        String custId = order.getCustomerId();
        String itemId = order.getItemId();
        int qty = order.getQuantity();
        if (custId != null && itemId != null && qty != 0) {
            return UUID.randomUUID().toString();
        }
        FaultBean faultBean = new FaultBean();
        faultBean.setFaultCode("Client");
        faultBean.setFaultString("Invalid request");
        faultBean.setFaultCodeURL("http://test.com/faultcodes");
        throw new InvalidMyOrderException("Insufficient information to place order, required custId, itemId and qnty", faultBean);
    }
}

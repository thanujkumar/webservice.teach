package cxf.frontend.simplefrontend;

public interface MyOrderProcess {
    String processMyOrder(MyOrder order) throws InvalidMyOrderException;
}

package cxf.frontend.jaxws.contractfirst.ex1;

import demo.order.ObjectFactory;
import demo.order.Order;
import demo.order.OrderProcess;
import demo.order.OrderProcessService;

import javax.xml.namespace.QName;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import java.util.List;

public class Client7WithoutWSDL {
    private static String pingUrl = "http://localhost:9999/cxfws/ex1/OrderProcess3";

    public static void main(String[] args) {
        QName qName = new QName("http://order.demo", "processOrder");
        OrderProcessService processOrderService = new OrderProcessService(null, qName);
        OrderProcess orderProcess = processOrderService.getOrderProcessPort();
        BindingProvider bindingProvider = (BindingProvider) orderProcess;
        Binding binding = bindingProvider.getBinding();
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, pingUrl);

        //Log Message
        List<Handler> handlerChain = binding.getHandlerChain();
        handlerChain.add(new LogMessageHandler());
        binding.setHandlerChain(handlerChain);

        ObjectFactory factory = new ObjectFactory();
        Order order = factory.createOrder();
        order.setCustomerID("123A");
        order.setItemID("SomeItemNoWsdl");
        order.setPrice(2.3);
        order.setQty(10);

        String uid = orderProcess.processOrder(order);
        System.out.println(uid);
    }
}

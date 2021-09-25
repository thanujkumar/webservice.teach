package cxf.frontend.jaxws.contractfirst.ex1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.order.Order;
import demo.order.OrderProcess;

//Need to run maven jetty
public class Client4Spring {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:client-applicationContext.xml"});
		OrderProcess port = (OrderProcess) context.getBean("orderProcess");
		
		Order order = new Order();
		order.setCustomerID("123A");
		order.setItemID("SomeItem");
		order.setPrice(2.3);
		order.setQty(10);
		System.out.println(port.processOrder(order));
 	}

}

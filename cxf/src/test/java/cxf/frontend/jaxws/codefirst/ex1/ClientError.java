package cxf.frontend.jaxws.codefirst.ex1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

//Check the logs/cxf/wspayload.log
public class ClientError {

	//Below uses cxf client configuration from spring configuration
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"cxf/frontend/jaxws/codefirst/ex1/client-ex1.xml"});
	    OrderProcess client = (OrderProcess) context.getBean("orderClient");
		

		Order order = new Order();
		//order.setCustomerId("123A");
		order.setItemId("SomeItem");
		order.setPrice(2.3);
		order.setQuantity(10);
	    System.out.println( client.processOrder(order) );
	}

}

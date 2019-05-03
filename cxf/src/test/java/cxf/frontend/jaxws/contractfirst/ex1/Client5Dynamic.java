package cxf.frontend.jaxws.contractfirst.ex1;

import java.util.Arrays;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import demo.order.Order;

public class Client5Dynamic {

	
	public static void main(String[] args) throws Exception {
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:9999/cxfws/ex1/OrderProcess3?wsdl");
		

		Order order = new Order();
		order.setCustomerID("123A");
		order.setItemID("SomeItem");
		order.setPrice(2.3);
		order.setQty(10);
		
		Object[] response = client.invoke("processOrder", order);
		System.out.println(Arrays.toString(response));
	}
}

package cxf.frontend.jaxws.dispatch_provider;

import javax.xml.ws.Endpoint;

public class ServerForSourceProvider {
   
	static String address = "http://localhost:9191/orderProcessSourceProvider";

	
	public static void main(String[] args) throws Exception {
		System.out.println("Starting server....");
		Object provider = new OrderProcessSourceProvider();
		Endpoint endPoint = Endpoint.publish(address, provider);
		System.out.println("Server available at :" + address);
		
	}
}

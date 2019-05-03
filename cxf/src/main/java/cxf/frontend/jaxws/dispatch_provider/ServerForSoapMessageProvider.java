package cxf.frontend.jaxws.dispatch_provider;

import javax.xml.ws.Endpoint;

public class ServerForSoapMessageProvider {
	static String address = "http://localhost:9292/orderProcessSourceProvider";

	public static void main(String[] args) throws Exception {
		System.out.println("Starting server....");
		Object provider = new OrderProcessSoapMessageProvider();
		Endpoint endPoint = Endpoint.publish(address, provider);
		System.out.println("Server available at :" + address);

	}
}

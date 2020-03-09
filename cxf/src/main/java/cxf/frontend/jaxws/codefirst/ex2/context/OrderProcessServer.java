package cxf.frontend.jaxws.codefirst.ex2.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class OrderProcessServer {
	
	public static void main(String[] args) {
		System.out.println("Starting server....");
		OrderProcess orderProcess = new OrderProcessImpl();
		//ServerFactoryBean fb;
		JaxWsServerFactoryBean wsServer = new JaxWsServerFactoryBean(); 
		wsServer.setServiceClass(OrderProcess.class); // if annotation settings needs to work - like serviceName, portName etc
		wsServer.setServiceBean(orderProcess);
		wsServer.setAddress("http://localhost:9292/orderProcess4");
		LoggingFeature logging = new LoggingFeature();
		logging.setPrettyLogging(true);
		wsServer.getFeatures().add(logging);
		
		wsServer.create();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Stopping server....");
		System.exit(0);
		
	}

}

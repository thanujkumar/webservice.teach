package jaxws.basic;

import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;


public class RandomGeneratorPublisher {
	
	static {
		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
		
		//LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINE);
		Logger rootLogger = Logger.getLogger("");
		rootLogger.setLevel(Level.ALL);
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.FINE);
		rootLogger.addHandler(consoleHandler);
	}

	final static String url = "http://localhost:8888/rs";
	
	public static void main(String[] args) {
	  System.out.printf("Publish random service at endpoint %s", url);	
	  //Endpoint.publish(url, new RandomGeneratorService());//Available from JDK 1.6 onwards
	                   //OR
	  //With SoapLoggingHandler
	  Endpoint soapEP = Endpoint.create(new RandomGeneratorService());
	  List<Handler> handlerChain = soapEP.getBinding().getHandlerChain();
	  handlerChain.add(new SoapLoggingHandler());
	  soapEP.getBinding().setHandlerChain(handlerChain);
	  soapEP.publish(url);
	}
}

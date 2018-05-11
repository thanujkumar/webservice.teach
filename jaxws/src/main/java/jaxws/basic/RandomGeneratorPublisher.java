package jaxws.basic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.xml.ws.Endpoint;


public class RandomGeneratorPublisher {
	
	static {
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
	  Endpoint.publish(url, new RandomGeneratorService());//Available from JDK 1.6 onwards
	}
}

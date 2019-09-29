package cxf.internals;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.io.CachedConstants;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.transport.servlet.CXFServlet;

public class CustomLoadingOfBus {

	public static void main(String[] args) {
		CXFServlet servlet;
		SpringBusFactory busFactory = new SpringBusFactory();
		//busFactory.createBus("cxf.xml");
		
		//Bus bus = busFactory.createBus();
		Bus bus = BusFactory.getThreadDefaultBus(true);

		System.out.println(bus.getProperty(CachedConstants.THRESHOLD_BUS_PROP));
		for (String key : bus.getProperties().keySet()) {
			System.out.println(key +" : "+ bus.getProperty(key));
		}

		ServiceInfo info;
		
		//BusFactory.getDefaultBus();

	}
}

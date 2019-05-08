package cxf.internals;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.transport.servlet.CXFServlet;

public class CustomLoadingOfBus {

	public static void main(String[] args) {
		CXFServlet servlet;
		SpringBusFactory busFactory = new SpringBusFactory();
		//busFactory.createBus("cxf.xml");
		
		Bus bus = busFactory.createBus();
		
		ServiceInfo info;
		
		//BusFactory.getDefaultBus();
	}
}

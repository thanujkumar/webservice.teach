package org.tk.springws.service;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;

public class CountryService {
	
	WebServiceMessage msg;
	WebServiceMessageFactory msf;

	TransportContextHolder tch;
}

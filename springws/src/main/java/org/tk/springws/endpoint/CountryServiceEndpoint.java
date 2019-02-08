package org.tk.springws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.tk.domain.countryprocessingaction.v1.CountryType;
import org.tk.springws.service.CountryService;

@Endpoint //(Special sort of @Component)
public class CountryServiceEndpoint {

	private static final String NAMESPACE_URI = "urn:tk.org:ws:countryProcessingAction:v1";
	
	@Autowired
	private CountryService countryService;
	
	
	public CountryType createCountry (CountryType countryInfo) {
		
		return null;
	}
}

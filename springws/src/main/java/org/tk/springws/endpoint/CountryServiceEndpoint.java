package org.tk.springws.endpoint;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.tk.domain.countryprocessingaction.v1.CountryType;
import org.tk.springws.service.CountryService;

@Endpoint // (Special sort of @Component)
public class CountryServiceEndpoint {

	private static final String NAMESPACE_URI = "urn:tk.org:ws:countryProcessingAction:v1";

	@Autowired
	private CountryService countryService;

	@PayloadRoot(localPart = "createCountryRequest", namespace = "urn:tk.org:ws:countryProcessingAction:v1")
    @ResponsePayload
	public CountryType createCountryRequest(@RequestPayload CountryType countryInfo) throws DatatypeConfigurationException {
        System.out.println(countryInfo);
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(new Date());
        countryInfo.setCreatedTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal));
        countryInfo.setId(1L);
 		return countryInfo;
	}
}

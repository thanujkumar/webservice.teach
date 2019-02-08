package org.tk.springws.endpoint;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.tk.domain.countryprocessingaction.v1.CountryType;
import org.tk.springws.service.CountryService;
import org.tk.ws.countryprocessingaction.v1.ObjectFactory;

@Endpoint // (Special sort of @Component)
public class CountryServiceEndpoint {

	private static final String NAMESPACE_URI = "urn:tk.org:ws:countryProcessingAction:v1";

	@Autowired
	private CountryService countryService;
	
	//http://blog.bdoughan.com/2012/07/jaxb-and-root-elements.html
	
	//As the element is global no @XmlRootElement annotation is generated

//	@PayloadRoot(localPart = "createCountryRequest", namespace = "urn:tk.org:ws:countryProcessingAction:v1")
//    @ResponsePayload
//	public CountryType createCountryRequest(@RequestPayload CountryType countryInfo) throws DatatypeConfigurationException {
//        System.out.println(countryInfo);
//        GregorianCalendar gcal = new GregorianCalendar();
//        gcal.setTime(new Date());
//        countryInfo.setCreatedTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal));
//        countryInfo.setId(1L);
// 		return countryInfo;
//	}
	
	
	@PayloadRoot(localPart = "createCountryRequest", namespace = "urn:tk.org:ws:countryProcessingAction:v1")
    @ResponsePayload
	public JAXBElement<CountryType> createCountryRequest(@RequestPayload JAXBElement<CountryType> countryInfoEle) throws DatatypeConfigurationException {
		CountryType countryInfo = (CountryType) JAXBIntrospector.getValue(countryInfoEle);
        System.out.println(countryInfo);
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(new Date());
        countryInfo.setCreatedTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal));
        countryInfo.setId(1L);
 		return new ObjectFactory().createCreateCountyResponse(countryInfo);
	}

}

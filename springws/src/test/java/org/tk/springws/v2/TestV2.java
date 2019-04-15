package org.tk.springws.v2;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.tk.domain.countryprocessingaction.v2.Country;

public class TestV2 {

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
		JAXBContext context = JAXBContext.newInstance("org.tk.ws.countryprocessingaction.v2");
		Country type = new Country();
		type.setCreatedBy("TK");
		type.setName("INDIA");

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		type.setCreatedTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

		Unmarshaller unmarshaller = context.createUnmarshaller();
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		try {
			// As CountryType has no @XmlRootElement this would fail
			marshaller.marshal(type, System.out); // will fail

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

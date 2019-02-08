

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.tk.domain.countryprocessingaction.v1.CountryType;
import org.tk.ws.countryprocessingaction.v1.ObjectFactory;

public class Test_JAXB {

	public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {
		JAXBContext context = JAXBContext.newInstance("org.tk.ws.countryprocessingaction.v1");
		CountryType type = new CountryType();
		type.setCreatedBy("E212731");
		type.setName("INDIA");

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		type.setCreatedTimestamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc));

		Unmarshaller unmarshaller = context.createUnmarshaller();
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		// As CountryType has no @XmlRootElement
		ObjectFactory factory = new ObjectFactory();
		JAXBElement<CountryType> countryTypeElement = factory.createCreateCountryRequest(type);
		marshaller.marshal(countryTypeElement, System.out);

		try {
			// As CountryType has no @XmlRootElement this would fail
			marshaller.marshal(type, System.out); // will fail

		} catch (Exception e) {
			e.printStackTrace();
		}

		ObjectFactory of = new ObjectFactory();
		JAXBElement<CountryType> ct = of.createCreateCountryRequest(type);
		marshaller.marshal(ct, System.out);
	}
}

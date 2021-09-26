package cxf.frontend.jaxws.codefirst.ex4.jaxws;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class HelloWorldJaxWsClient2 {
    private static final QName SERVICE_NAME =
            new QName("http://jaxws.ex4.codefirst.jaxws.frontend.cxf/", "HelloWorldJaxWsImplService"); // as per generated wsdl <wsdl:service name="HelloWorldJaxWsImplService">
    private static final QName PORT_NAME =
            new QName("http://jaxws.ex4.codefirst.jaxws.frontend.cxf/", "HelloWorldJaxWsImplPort"); // as per generated wsdl <wsdl:port binding="tns:HelloWorldJaxWsImplServiceSoapBinding" name="HelloWorldJaxWsImplPort">

    private static final String WSDL_LOCATION =  "http://localhost:5000/HelloServerPort?wsdl";

    public static void main(String[] args) throws MalformedURLException {
        URL wsdlURL = new URL(WSDL_LOCATION);
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        HelloWorldJaxWs helloWorldJaxWs = service.getPort(PORT_NAME, HelloWorldJaxWs.class);
        System.out.println(helloWorldJaxWs.greetings("thanuj"));
    }
}

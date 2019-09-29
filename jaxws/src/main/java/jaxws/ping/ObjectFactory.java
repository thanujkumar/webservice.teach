
package jaxws.ping;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxws2.ping package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AuthHeader_QNAME = new QName("http://tk.org/header/AuthConfig_v10", "AuthHeader");
    private final static QName _PingResponse_QNAME = new QName("urn:tk.org:ws:common:ping", "pingResponse");
    private final static QName _PingInputArgs_QNAME = new QName("urn:tk.org:ws:common:ping", "PingInputArgs");
    private final static QName _ResponseStateTypeRequestTraceId_QNAME = new QName("http://tk.org/header/AuthConfig_v10", "requestTraceId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxws2.ping
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PingRequest }
     * 
     */
    public PingRequest createPingRequest() {
        return new PingRequest();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link Service }
     * 
     */
    public Service createService() {
        return new Service();
    }

    /**
     * Create an instance of {@link ServiceList }
     * 
     */
    public ServiceList createServiceList() {
        return new ServiceList();
    }

    /**
     * Create an instance of {@link AuthHeaderType }
     * 
     */
    public AuthHeaderType createAuthHeaderType() {
        return new AuthHeaderType();
    }

    /**
     * Create an instance of {@link ErrorItemType }
     * 
     */
    public ErrorItemType createErrorItemType() {
        return new ErrorItemType();
    }

    /**
     * Create an instance of {@link RequestContextType }
     * 
     */
    public RequestContextType createRequestContextType() {
        return new RequestContextType();
    }

    /**
     * Create an instance of {@link MapType }
     * 
     */
    public MapType createMapType() {
        return new MapType();
    }

    /**
     * Create an instance of {@link ResponseStateType }
     * 
     */
    public ResponseStateType createResponseStateType() {
        return new ResponseStateType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthHeaderType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tk.org/header/AuthConfig_v10", name = "AuthHeader")
    public JAXBElement<AuthHeaderType> createAuthHeader(AuthHeaderType value) {
        return new JAXBElement<AuthHeaderType>(_AuthHeader_QNAME, AuthHeaderType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:tk.org:ws:common:ping", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:tk.org:ws:common:ping", name = "PingInputArgs")
    public JAXBElement<PingRequest> createPingInputArgs(PingRequest value) {
        return new JAXBElement<PingRequest>(_PingInputArgs_QNAME, PingRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tk.org/header/AuthConfig_v10", name = "requestTraceId", scope = ResponseStateType.class)
    public JAXBElement<Long> createResponseStateTypeRequestTraceId(Long value) {
        return new JAXBElement<Long>(_ResponseStateTypeRequestTraceId_QNAME, Long.class, ResponseStateType.class, value);
    }

}

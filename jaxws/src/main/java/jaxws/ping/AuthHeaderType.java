
package jaxws.ping;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthHeaderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SrcApp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DestApp" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceToBeCalled" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RequestContext" type="{http://tk.org/header/AuthConfig_v10}RequestContextType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthHeaderType", propOrder = {
    "srcApp",
    "destApp",
    "serviceToBeCalled",
    "serviceVersion",
    "requestContext"
})
public class AuthHeaderType {

    @XmlElement(name = "SrcApp", required = true)
    protected String srcApp;
    @XmlElement(name = "DestApp", required = true)
    protected String destApp;
    @XmlElement(name = "ServiceToBeCalled", required = true)
    protected String serviceToBeCalled;
    @XmlElement(name = "ServiceVersion", required = true)
    protected String serviceVersion;
    @XmlElement(name = "RequestContext", required = true)
    protected RequestContextType requestContext;

    /**
     * Gets the value of the srcApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcApp() {
        return srcApp;
    }

    /**
     * Sets the value of the srcApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcApp(String value) {
        this.srcApp = value;
    }

    /**
     * Gets the value of the destApp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestApp() {
        return destApp;
    }

    /**
     * Sets the value of the destApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestApp(String value) {
        this.destApp = value;
    }

    /**
     * Gets the value of the serviceToBeCalled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceToBeCalled() {
        return serviceToBeCalled;
    }

    /**
     * Sets the value of the serviceToBeCalled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceToBeCalled(String value) {
        this.serviceToBeCalled = value;
    }

    /**
     * Gets the value of the serviceVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceVersion() {
        return serviceVersion;
    }

    /**
     * Sets the value of the serviceVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceVersion(String value) {
        this.serviceVersion = value;
    }

    /**
     * Gets the value of the requestContext property.
     * 
     * @return
     *     possible object is
     *     {@link RequestContextType }
     *     
     */
    public RequestContextType getRequestContext() {
        return requestContext;
    }

    /**
     * Sets the value of the requestContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestContextType }
     *     
     */
    public void setRequestContext(RequestContextType value) {
        this.requestContext = value;
    }

}

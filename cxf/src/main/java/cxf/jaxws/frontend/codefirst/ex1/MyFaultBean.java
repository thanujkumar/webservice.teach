package cxf.jaxws.frontend.codefirst.ex1;

import lombok.Data;

/* https://java.globinch.com/enterprise-java/web-services/jax-ws/jax-ws-exceptions-faults-annotation-exception-and-fault-handling-examples/ 
 * https://io.typepad.com/eben_hewitt_on_java/2009/07/using-soap-faults-and-exceptions-in-java-jaxws-web-services.html
 * 
 */
@Data
public class MyFaultBean {

	private String faultCode;
	private String faultString;
	private String faultCodeURL;
}

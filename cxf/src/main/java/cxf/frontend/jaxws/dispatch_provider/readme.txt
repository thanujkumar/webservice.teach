Provider and Dispatch interfaces, part of JAX-WS API are used to develop a web
service that processes or handles messages as raw XML, and not through method
invocation. Unlike SEI-based implementation, the messages are not converted into
XML using data binding techniques like JAXB. Instead, the messages themselves
are in a raw XML format.

The Provider and Dispatch methods are useful where the XML messages
transferred between web service client and web service provider are pretty large and
you don't want the extra overhead of converting the XML messages in Java objects.
As part of your web service implementation, you would want to deal with XML
directly and probably use an effective way to parse XML, rather than relying on the
web service framework.

JAX-WS provides the javax.xml.ws.Provider interface that offers functionality
to create and implement a service provider that will process XML messages. It will
take a request in the form of XML messages from the dispatcher client, process the
same, and accordingly generate the response. The provider implementation class
will be published as a service endpoint on the server. On the other hand, the JAX-WS
javax.xml.ws.Dispatch interface is used to process the XML message and send the
response in XML format to the service provider.

The Provider and Dispatcher interfaces allow two types of messages—Message
and Payload. They are often considered as two different messaging modes. The Message mode consists of the actual data
along with the control information such as the header, whereas the Payload mode
works only with actual data The Message mode is used when you want to access the
SOAP header information associated with the web service request. Most web service
specifications such as WS-Security, WS-Policy, and WS-authorization use the SOAP
header to propagate context information for web services.

Both the Provider and Dispatch implementation work with input and output
messages that represent one of the following three objects—javax.xml.transform.
Source, javax.xml.soap.SOAPMessage, and javax.activation.DataSource.

javax.xml.transform.Source - The Provider implementation works with three types of source
implementation, DOMSource, SAXSource, and StreamSource.

javax.xml.soap.SOAPMessage -  The SOAPMessage object is a natural choice for an input or output message for the
								Provider implementation, if you are using the SOAP binding for transmitting
								messages. It works only in the Message mode, which means one has to provide
								the complete SOAP envelope as messages. A SOAPMessage object represents the
								SOAP envelope which is composed of a SOAPPart object and an AttachmentPart
								object. The SOAPPart resembles the SOAP envelope, and it contains SOAP headers
								and the SOAP message body. The AttachmentPart object resembles binary data
								attached to the SOAP message. The SOAPMessage object can have zero or several
								AttachmentPart objects.

javax.activation.DataSource -  You can also pass messages as a form of DataSource object. The DataSource object
								represents an arbitrary collection of data that supports MIME type. It provides access
								to the data type and the data itself in the form of InputStream and OutputStream. The
								DataSource object is useful when you want to send messages that are http-bound with
								the mode as Message. An example of using a DataSource object would be to transfer
								image contents such as like fingerprint images for authentication.
								

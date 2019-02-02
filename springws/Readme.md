#####Spring Web Services supports the following standards:

* SOAP 1.1 and 1.2
* WSDL 1.1 and 2.0 (XSD-based generation only supported for WSDL 1.1)
* WS-I Basic Profile 1.0, 1.1, 1.2 and 2.0
* WS-Addressing 1.0 and the August 2004 draft
* SOAP Message Security 1.1, Username Token Profile 1.1, X.509 Certificate Token Profile 1.1, SAML Token Profile 1.1, Kerberos Token Profile 1.1, Basic Security Profile 1.1

##### Data Contract
* DTDs
* XML Schema (XSD)
* RELAX NG
* Schematron

##### Service contract
A service contract is generally expressed as a WSDL file. Note that in Spring-WS, writing the WSDL by hand is not required. Based on the XSD and some conventions, Spring-WS can create the WSDL for you

Spring-WS only supports the contract-first development style

[Literal vs. Encoded, RPC- vs. Document-Style](https://www.ibm.com/support/knowledgecenter/en/SSB27H_6.2.0/fa2ws_ovw_soap_syntax_lit.html)

A WSDL document (WSDL = Web Service Description Language) describes a web service. A WSDL binding describes how the service is bound to a messaging protocol, particularly the SOAP messaging protocol. A WSDL SOAP binding can be either a Remote Procedure Call (RPC) style binding or a document style binding. A SOAP binding can also have an encoded use or a literal use. This gives four style/use models:

* RPC/encoded.
* RPC/literal.
* Document/encoded (not used in practice).
* Document/literal.

WSDL distinguishes between two message styles: document and RPC. The message style affects the contents of the SOAP Body:

* __Document style:__ The SOAP Body contains one or more child elements called parts. There are no SOAP formatting rules for what the body contains; it contains whatever the sender and the receiver agrees upon.


* __RPC style:__ RPC implies that SOAP body contains an element with the name of the method or operation being invoked. This element in turn contains an element for each parameter of that method/operation.

For applications that use serialization/deserialization to abstract away the data wire format, there is one more choice to be made: the serialization format. There are two popular serialization formats:

* **SOAP Encoding:** SOAP encoding is a set of serialization. The rules specify how objects, structures, arrays, and object graphs should be serialized. Generally speaking, an application using SOAP encoding is focused on remote procedure calls and will likely use RPC message style. When SOAP encoding is used, the SOAP message contains data type information within the SOAP message. This makes serialization (data translation) easier since the data type of each parameter is denoted with the parameter.


* **Literal:** Data is serialized according to a schema. In practice, this schema is usually expressed using W3C XML Schema. The SOAP message does not directly contain any data type information, just a reference (namespace) to the schema that is used. To perform proper serialization (data translation) both, the sender and the receiver, must know the schema and must use the same rules for translating data.

**Examples:**
------------
[Which style of WSDL should I use?](https://www.ibm.com/developerworks/library/ws-whichwsdl/index.html)

There are 2 namespaces that are used here: **xsi** and **xsd**. Both are defined within the SOAP envelope (not shown in examples). The **xsi** namespace is the schema instance [http://www.w3.org/2001/XMLSchema-instance](http://www.w3.org/2001/XMLSchema-instance) and defines the type attribute (besides others), **xsd** is the schema namespace [http://www.w3.org/2001/XMLSchema](http://www.w3.org/2001/XMLSchema), and it defines the meaning of the data types like int or float.

The following SOAP message uses **RPC style** and **SOAP encoding**:

The SOAP message contains an operation name (myMethod). The method transports 2 parameters, x and y, which both specify its data type (int and float) through the type attributes.

```xml
<soap:envelope>
    <soap:body>
        <myMethod>
            <x xsi:type="xsd:int">5</x>
            <y xsi:type="xsd:float">5.0</y>
        </myMethod>
    </soap:body>
</soap:envelope>
```
The following SOAP message uses **RPC style** and **literal**:

In contrast to SOAP encoding, the parameters x and y do not specify any data type within the SOAP message. The sender and the receiver must ‘know’ what data type the parameters are. Usually that information is available in the WSDL.

```xml
<soap:envelope>
    <soap:body>
        <myMethod>
            <x>5</x>
            <y>5.0</y>
        </myMethod>
    </soap:body>
</soap:envelope>
```
The following SOAP message uses **document style** and **literal**:

Here, there is no method or operation name part of the body. Instead, the body directly contains the parameters. Also, there is no data type information that is contained in the SOAP message. Everything that appears within the body is defined in a schema.

```xml
<soap:envelope>
    <soap:body>
        <x>5</x>
        <y>5.0</y>
    </soap:body>
</soap:envelope>
```

The following SOAP message uses **document style** and **literal wrapped**:

This SOAP message looks much the same as the RPC/literal example. Here, the myMethod tag does not specify the method name, but a wrapper element, which the single input message's part 

```xml
<soap:envelope>
    <soap:body>
        <myMethod>
            <x>5</x>
            <y>5.0</y>
        </myMethod>
    </soap:body>
</soap:envelope>
```


[Implement implicit and explicit SOAP headers](https://www.ibm.com/developerworks/library/ws-tip-headers/index.html)

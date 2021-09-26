Web service context
Every message that is exchanged between the client and service provider has some
contextual information attached to it. The context here is the web service that gives
information about the service message being passed between the endpoints. The
context information is often called metadata, that is, the data about the message. The
context information is stored in the form of key-value pairs. Context information
is simply the properties that provide information on the incoming and outgoing
message. The properties are stored as a Java Map object. These properties hold two
types of information, data about the message and the underlying transport protocol
that is used to route the message.
CXF provides access to these context properties in the form of a JAX-WS based
MessageContext object. The javax.xml.ws.handler.MessageContext interface
extends java.util.Map<String key, Object obj>. The Message context object
is associated with a scope and can be in any one of the following scopes:
• Application
The message context properties defined in an application scope can be
shared by a service provider, service consumer, and the JAX-WS
handler implementations. Any message context property set in service
consumer code or service provider code is defaulted to Application scope.
• Handler
Handler scoped properties are only available to the JAX-WS handler
implementations. A message context property set in Handlers is not
available to the service implementation code or a service client. Any
message context property defined by the handler implementation is,
by default, handler scoped.

You can change the scope with the help of the setScope method of the
MessageContext object. The setScope method takes two parameters, namely,the
key and the scope. The key is the message context property key that you want to
change to reflect the new scope. The scope value can be MessageContext.Scope.
APPLICATION or MessageContext.Scope.HANDLER.
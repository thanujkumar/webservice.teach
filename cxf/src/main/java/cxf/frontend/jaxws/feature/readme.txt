Understanding CXF features
A Feature is a component that provides extra capability to the server, client, and
bus, over and above their existing functionality. The CXF bundle provides feature
components that allow the developer to add extra features to the endpoints and bus.

Features are an indirect form of interceptors. You can use a Feature component
instead of directly using an interceptor. When you apply a feature to the service
endpoint, the server bean factory will invoke the initialize method of that
particular feature class. This method will invoke the respective interceptor class
for that feature. For example, you can use LoggingFeature to enable logging of
inbound and outbound messages. The LoggingFeature class, behind the scenes,
will invoke the initialize method which will register the LoggingInInterceptor
and LoggingOutInterceptor components so as to perform the logging. With
features, you can avoid direct use of interceptors. It is the most convenient way of
applying extra functionality to your web service. Every feature class extends the
AbstractFeature class. This class provides API to add extra capabilities to the
server, client, or bus. When the feature is applied to the bus, all the service endpoints
automatically inherit that feature.
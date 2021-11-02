package cxf.frontend.jaxrs.ex1;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InterceptorProvider;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.EndpointInfo;
import org.apache.cxf.service.model.ServiceInfo;

import javax.xml.namespace.QName;

public class MsgLoggingFeature extends AbstractFeature {

    @Override
    public void doInitializeProvider(InterceptorProvider provider, Bus bus) {
        provider.getInInterceptors().add(new AbstractPhaseInterceptor<Message>(Phase.INVOKE) {
            @Override
            public void handleMessage(Message message) throws Fault {
                Endpoint ep = message.getExchange().getEndpoint();
                EndpointInfo endpointInfo = (ep == null) ? new EndpointInfo() : ep.getEndpointInfo();
                BindingInfo bindingInfo = ep.getBinding().getBindingInfo(); //check bindingid ends in jaxrs or check Slf4jEventSender
                ServiceInfo serviceInfo = endpointInfo.getService();
                QName name = null;
                if (serviceInfo != null) {
                    name = serviceInfo.getName();
                }
                System.out.println("Is REST : " + message.get(Message.REST_MESSAGE));
                System.out.println("WS Request type : " + (name != null ? "SOAP" : "REST"));
                System.out.println("URI : " + message.get(Message.REQUEST_URI));
                System.out.println("MSG => " + PhaseInterceptorChain.getCurrentMessage());
            }
        });

        provider.getOutInterceptors().add(new AbstractPhaseInterceptor<Message>(Phase.PREPARE_SEND) {
            @Override
            public void handleMessage(Message message) throws Fault {
                System.out.println("MSG <= " + PhaseInterceptorChain.getCurrentMessage());
            }
        });
        super.doInitializeProvider(provider, bus);
    }
}

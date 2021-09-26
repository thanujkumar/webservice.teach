package cxf.frontend.jaxws.feature;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.InterceptorProvider;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class FeatLogPhaseFeature extends AbstractFeature {
    //Look at the methods defined in Feature
    Feature f;

    @Override
    public void doInitializeProvider(InterceptorProvider provider, Bus bus) {
        super.doInitializeProvider(provider, bus);

        provider.getInInterceptors().add(new AbstractPhaseInterceptor<Message>(Phase.READ) {
            @Override
            public void handleMessage(Message message) throws Fault {
                System.out.println("IN Current Phase ==> " + getPhase());
                System.out.println("IN Before ==> " + getBefore());
                System.out.println("IN After  ==> " + getAfter());
                System.out.println("IN Message ==>" + message);
            }
        });

        provider.getOutInterceptors().add(new AbstractPhaseInterceptor<Message>(Phase.SEND) {
            @Override
            public void handleMessage(Message message) throws Fault {
                System.out.println("OUT Current Phase ==> " + getPhase());
                System.out.println("OUT Before ==> " + getBefore());
                System.out.println("OUT After  ==> " + getAfter());
                System.out.println("OUT Message ==>" + message);
            }
        });
    }
}

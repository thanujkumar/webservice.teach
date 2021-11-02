package cxf.frontend.jaxws.interceptors;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
/*
  INBOUND PHASES

  RECEIVE                   ===> Transport level processing
  (PRE/USER/POST)_STREAM    ===> Stream level processing/transformations
  READ                      ===> Suitable for reading headers
  (PRE/USER/POST)_PROTOCOL  ===> Protocol processing
  UNMARSHAL                 ===> Unmarshalling of the request
  (PRE/USER/POST)_LOGICAL   ===> Processing of the unmarshalled request
  PRE_INVOKE                ===> Pre invocation actions
  INVOKE                    ===> Invocation of the service
  POST_INVOKE               ===> Invocation of the outgoing chain if there is one

 OUTBOUND PHASES

 SETUP                   ===> Setup for the following phases
 (PRE/USER/POST)_LOGICAL ===> Processing of objects about to be marshalled
 PREPARE_SEND            ===> Opening of the connection
 PRE_STREAM              ===> Stream level processing
 PRE_PROTOCOL            ===> Misc protocol actions
 */

//Check  org.apache.cxf.phase.PhaseManager implementation to know the phases

//Just sample TODO not used
public class MyPhaseInterceptor extends AbstractPhaseInterceptor  /* PhaseInterceptor implements Interceptor*/{


    public MyPhaseInterceptor() {
        super(Phase.INVOKE);
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        System.out.println(getBefore());
        System.out.println(message);
        System.out.println(getAfter());
    }

    @Override
    public void handleFault(Message message) {
        super.handleFault(message);
    }
}

/*
The getAfter and getBefore return the set of IDs of interceptors
participating in the same phase as that of the interceptor on which these
methods are invoked.
If you do not wish to have your interceptor participate in the phase, then
your interceptor can directly implement the Interceptor interface and
should not use the PhaseInterceptor interface
 */
package cxf.frontend.jaxws.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptorChain;

public class MyClientInInterceptor extends AbstractSoapInterceptor {

    public MyClientInInterceptor() {
        //Change phases to know how SoapMessage is built
        super(Phase.READ);
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        System.out.println("IN-C Current Phase ==> " + getPhase());
        System.out.println("InMessage : "+ PhaseInterceptorChain.getCurrentMessage().getExchange().getInMessage());
        System.out.println("IN-C Before ==> " + getBefore());
        System.out.println("IN-C After  ==> " + getAfter());
        System.out.println("IN-C SoapMessage ==>" + soapMessage);
    }
}

/*

  INBOUND PHASES
 ----------------
     RECEIVE                   ===> Transport level processing
     (PRE/USER/POST)_STREAM    ===> Stream level processing/transformations
     READ                      ===> Suitable for reading headers
     (PRE/USER/POST)_PROTOCOL  ===> Protocol processing
     UNMARSHAL                 ===> Unmarshalling of the request
     (PRE/USER/POST)_LOGICAL   ===> Processing of the unmarshalled request
     PRE_INVOKE                ===> Pre invocation actions
     INVOKE                    ===> Invocation of the service
     POST_INVOKE               ===> Invocation of the outgoing chain if there is one

RECEIVE = "receive";
PRE_STREAM = "pre-stream";
PRE_STREAM_ENDING = "pre-stream-ending";
USER_STREAM = "user-stream";
USER_STREAM_ENDING = "user-stream-ending";
POST_STREAM = "post-stream";
POST_STREAM_ENDING = "post-stream-ending";
READ = "read";
PRE_PROTOCOL_FRONTEND = "pre-protocol-frontend";
PRE_PROTOCOL = "pre-protocol";
PRE_PROTOCOL_ENDING = "pre-protocol-ending";
USER_PROTOCOL = "user-protocol";
USER_PROTOCOL_ENDING = "user-protocol-ending";
PROTOCOL = "protocol";
POST_PROTOCOL = "post-protocol";
POST_PROTOCOL_ENDING = "post-protocol-ending";
PRE_UNMARSHAL = "pre-unmarshal";
UNMARSHAL = "unmarshal";
POST_UNMARSHAL = "post-unmarshal";
PRE_LOGICAL = "pre-logical";
PRE_LOGICAL_ENDING = "pre-logical-ending";
USER_LOGICAL = "user-logical";
USER_LOGICAL_ENDING = "user-logical-ending";
POST_LOGICAL = "post-logical";
POST_LOGICAL_ENDING = "post-logical-ending";
PRE_INVOKE = "pre-invoke";
INVOKE = "invoke";
POST_INVOKE = "post-invoke";
 */

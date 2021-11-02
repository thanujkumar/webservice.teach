package cxf.frontend.jaxws.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptorChain;

//Check  org.apache.cxf.phase.PhaseManager implementation to know the phases


public class MyClientOutInterceptor extends AbstractSoapInterceptor {

    public MyClientOutInterceptor() {
        //Change phases to know how SoapMessage is built
        super(Phase.SEND);
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        System.out.println("OUT-C Current Phase ==> " + getPhase());
        System.out.println("OutMessage : "+ PhaseInterceptorChain.getCurrentMessage().getExchange().getOutMessage());
        System.out.println("Interceptor ID : "+ getId());
        System.out.println("OUT-C Before ==> " + getBefore());
        System.out.println("OUT-C After  ==> " + getAfter());
        System.out.println("OUT-C SoapMessage ==>" + soapMessage);
    }
}
/*
OUTBOUND PHASES
 ----------------
     SETUP                   ===> Setup for the following phases
     (PRE/USER/POST)_LOGICAL ===> Processing of objects about to be marshalled
     PREPARE_SEND            ===> Opening of the connection
     PRE_STREAM              ===> Stream level processing
     PRE_PROTOCOL            ===> Misc protocol actions
     WRITE                   ===> Writing of the protocol message
     MARSHAL                 ===> Marshalling of the objects
     (USER/POST)_PROTOCOL    ===> Processing of the protocol message
     (USER/POST)_STREAM      ===> Processing of the byte level message
     SEND                    ===> Final sending of the message and closing of the transport stream

 SETUP = "setup";
 SETUP_ENDING = "setup-ending";
 PRE_LOGICAL = "pre-logical";
 PRE_LOGICAL_ENDING = "pre-logical-ending";
 USER_LOGICAL = "user-logical";
 USER_LOGICAL_ENDING = "user-logical-ending";
 POST_LOGICAL = "post-logical";
 POST_LOGICAL_ENDING = "post-logical-ending";
 PREPARE_SEND = "prepare-send";
 PRE_STREAM = "pre-stream";
 PRE_STREAM_ENDING = "pre-stream-ending";
 PRE_PROTOCOL = "pre-protocol";
 PRE_PROTOCOL_ENDING = "pre-protocol-ending";
 WRITE = "write";
 WRITE_ENDING = "write-ending";
 PRE_MARSHAL = "pre-marshal";
 MARSHAL = "marshal";
 MARSHAL_ENDING = "marshal-ending";
 POST_MARSHAL = "post-marshal";
 USER_PROTOCOL = "user-protocol";
 PROTOCOL = "protocol";
 POST_PROTOCOL = "post-protocol";
 POST_PROTOCOL_ENDING = "post-protocol-ending";
 USER_STREAM = "user-stream";
 USER_STREAM_ENDING = "user-stream-ending";
 POST_STREAM = "post-stream";
 POST_STREAM_ENDING = "post-stream-ending";
 SEND = "send";
 PREPARE_SEND_ENDING = "prepare-send-ending";
 SEND_ENDING = "send-ending";

 */
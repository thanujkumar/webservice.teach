//Check  org.apache.cxf.phase.PhaseManager implementation to know the phases

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

//From org.apache.cxf.phase.PhaseManager implementation class
 final void createInPhases() {
        int i = 0;

        inPhases = new SortedArraySet<>();
        inPhases.add(new Phase(Phase.RECEIVE, ++i * 1000));
        inPhases.add(new Phase(Phase.PRE_STREAM, ++i * 1000));
        inPhases.add(new Phase(Phase.USER_STREAM, ++i * 1000));
        inPhases.add(new Phase(Phase.POST_STREAM, ++i * 1000));
        inPhases.add(new Phase(Phase.READ, ++i * 1000));
        inPhases.add(new Phase(Phase.PRE_PROTOCOL, ++i * 1000));
        inPhases.add(new Phase(Phase.PRE_PROTOCOL_FRONTEND, ++i * 1000));
        inPhases.add(new Phase(Phase.USER_PROTOCOL, ++i * 1000));
        inPhases.add(new Phase(Phase.PROTOCOL, ++i * 1000));
        inPhases.add(new Phase(Phase.POST_PROTOCOL, ++i * 1000));
        inPhases.add(new Phase(Phase.PRE_UNMARSHAL, ++i * 1000));
        inPhases.add(new Phase(Phase.UNMARSHAL, ++i * 1000));
        inPhases.add(new Phase(Phase.POST_UNMARSHAL, ++i * 1000));
        inPhases.add(new Phase(Phase.PRE_LOGICAL, ++i * 1000));
        inPhases.add(new Phase(Phase.USER_LOGICAL, ++i * 1000));
        inPhases.add(new Phase(Phase.POST_LOGICAL, ++i * 1000));
        inPhases.add(new Phase(Phase.PRE_INVOKE, ++i * 1000));
        inPhases.add(new Phase(Phase.INVOKE, ++i * 1000));
        inPhases.add(new Phase(Phase.POST_INVOKE, ++i * 1000));
    }

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

//From org.apache.cxf.phase.PhaseManager implementation class

 final void createOutPhases() {

        outPhases = new SortedArraySet<>();
        int i = 0;

        outPhases.add(new Phase(Phase.SETUP, ++i * 1000));
        outPhases.add(new Phase(Phase.PRE_LOGICAL, ++i * 1000));
        outPhases.add(new Phase(Phase.USER_LOGICAL, ++i * 1000));
        outPhases.add(new Phase(Phase.POST_LOGICAL, ++i * 1000));
        outPhases.add(new Phase(Phase.PREPARE_SEND, ++i * 1000));

        outPhases.add(new Phase(Phase.PRE_STREAM, ++i * 1000));

        outPhases.add(new Phase(Phase.PRE_PROTOCOL, ++i * 1000));
        outPhases.add(new Phase(Phase.PRE_PROTOCOL_FRONTEND, ++i * 1000));

        outPhases.add(new Phase(Phase.WRITE, ++i * 1000));
        outPhases.add(new Phase(Phase.PRE_MARSHAL, ++i * 1000));
        outPhases.add(new Phase(Phase.MARSHAL, ++i * 1000));
        outPhases.add(new Phase(Phase.POST_MARSHAL, ++i * 1000));
        outPhases.add(new Phase(Phase.USER_PROTOCOL, ++i * 1000));
        outPhases.add(new Phase(Phase.PROTOCOL, ++i * 1000));
        outPhases.add(new Phase(Phase.POST_PROTOCOL, ++i * 1000));

        outPhases.add(new Phase(Phase.USER_STREAM, ++i * 1000));
        outPhases.add(new Phase(Phase.POST_STREAM, ++i * 1000));

        outPhases.add(new Phase(Phase.SEND, ++i * 1000));

        //Make sure ending interceptors are put in positions symmetric
        // to their starting interceptors
        outPhases.add(new Phase(Phase.SEND_ENDING, ++i * 1000));

        outPhases.add(new Phase(Phase.POST_STREAM_ENDING, ++i * 1000));
        outPhases.add(new Phase(Phase.USER_STREAM_ENDING, ++i * 1000));

        outPhases.add(new Phase(Phase.POST_PROTOCOL_ENDING, ++i * 1000));
        outPhases.add(new Phase(Phase.USER_PROTOCOL_ENDING, ++i * 1000));

        outPhases.add(new Phase(Phase.MARSHAL_ENDING, ++i * 1000));
        outPhases.add(new Phase(Phase.WRITE_ENDING, ++i * 1000));

        outPhases.add(new Phase(Phase.PRE_PROTOCOL_ENDING, ++i * 1000));

        outPhases.add(new Phase(Phase.PRE_STREAM_ENDING, ++i * 1000));

        outPhases.add(new Phase(Phase.PREPARE_SEND_ENDING, ++i * 1000));
        outPhases.add(new Phase(Phase.POST_LOGICAL_ENDING, ++i * 1000));
        outPhases.add(new Phase(Phase.USER_LOGICAL_ENDING, ++i * 1000));
        outPhases.add(new Phase(Phase.PRE_LOGICAL_ENDING, ++i * 1000));
        outPhases.add(new Phase(Phase.SETUP_ENDING, ++i * 1000));

    }

package jaxws.ping;

import jaxws.basic.SoapLoggingHandler;

import javax.xml.ws.Endpoint;
import javax.xml.ws.handler.Handler;
import java.util.List;

public class MainPingPublisher {

    private static String url = "http://localhost:8888/ping";

    public static void main(String[] args) {
        Endpoint soapEP = Endpoint.create(new PingWs());
        List<Handler> handlerChain = soapEP.getBinding().getHandlerChain();
        handlerChain.add(new SoapLoggingHandler());
        soapEP.getBinding().setHandlerChain(handlerChain);
        soapEP.publish(url);
    }
}


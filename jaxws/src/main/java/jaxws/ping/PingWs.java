package jaxws.ping;



import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(
        //wsdlLocation = "wsdl/Ping.wsdl",
        targetNamespace = "urn:tk.org:ws:common:ping",
        endpointInterface = "jaxws.ping.PingPort",
        serviceName = "PingService",
        name = "PingPort",
        portName = "PingService")
public class PingWs implements PingPort {

    @Override
    public PingResponse ping(PingRequest pingRequestIn, AuthHeaderType soapHeader) {
        return inspect(soapHeader.getRequestContext().getEntityId());
    }

    private PingResponse inspect(String entityId) {
        PingResponse response = new PingResponse();
        response.setResponseState(new ResponseStateType());
        Service service = new Service();
        service.statusCode = 0;
        service.module = "module";
        service.name = "serviceName";
        service.statusMessage = "OK";
        List<Service> services = new ArrayList<>();
        services.add(service);
        ServiceList serviceList = new ServiceList();
        serviceList.service = services;
        response.setServiceList(serviceList);
        return response;
    }
}

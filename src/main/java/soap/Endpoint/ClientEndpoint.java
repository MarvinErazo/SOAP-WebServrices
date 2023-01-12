package soap.Endpoint;

import generated.GetClientRequest;
import generated.GetClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.Repository.ClientRepository;

@Endpoint
public class ClientEndpoint {
    public static final String URI = "http://soap.ws/SOAPWebServices";

    @Autowired
    private ClientRepository clientRepository;

    @PayloadRoot(namespace = URI, localPart = "getClientRequest")

    @ResponsePayload
    public GetClientResponse getClienteRequest (@RequestPayload GetClientRequest request){
        GetClientResponse response = new GetClientResponse();
        response.setCliente(clientRepository.buscarCliente(request.getNombre()));

        return response;
    }

}

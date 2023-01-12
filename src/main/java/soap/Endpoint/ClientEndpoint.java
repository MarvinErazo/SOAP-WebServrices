package soap.Endpoint;

import generated.GetClientRequest;
import generated.GetClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.Repository.ClientRepository;

/*
* Este componente proporciona una interfaz para acceder a un servicio web.
* expone un método que maneja una solicitud y devuelve una respuesta.
*/
@Endpoint
public class ClientEndpoint {
    public static final String URI = "http://soap.ws/SOAPWebServices";
    private ClientRepository clientRepository;

    //permite inyectar unas dependencias con otras dentro de Spring
    @Autowired
    public ClientEndpoint(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //Se utiliza para especificar el nombre local y el espacio de nombres de un elemento raíz
    // de un mensaje SOAP que debe ser manejado por un método de un endpoint.
    @PayloadRoot(namespace = URI, localPart = "getClientRequest")

    //@ResponsePayload indica que el valor de retorno de un método debe ser utilizado como
    // el cuerpo del mensaje SOAP de respuesta.

    //@RequestPayload indica que el parámetro de un método debe ser llenado con el cuerpo del mensaje SOAP de solicitud.
    @ResponsePayload
    public GetClientResponse getClienteRequest (@RequestPayload GetClientRequest request){
        GetClientResponse response = new GetClientResponse();
        response.setCliente(clientRepository.buscarCliente(request.getNombre(),request.getId()));

        return response;
    }

}

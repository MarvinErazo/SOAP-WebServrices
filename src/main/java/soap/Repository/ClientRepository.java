package soap.Repository;

import generated.Cliente;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/*
* Contiene una coleccion de clientes que devuelve el mismo parametro
*/
@Component
public class ClientRepository {

    private static final Map<String,Cliente> clientes = new HashMap<>();

    /*
     * Constructor que contiene una anotacion @PostConstruct que permite generar
     * clientes los cuales se vana solicitar en el servicio SOAP
     */
    @PostConstruct
    public void InitialData(){
        Cliente marvin = new Cliente();
        marvin.setId(1);
        marvin.setNombre("Marvin");
        marvin.setEmail("marvin@gmail.com");
        marvin.setPais("ECU");

        clientes.put(marvin.getNombre(),marvin);

        /*Cliente cl1 = new Cliente();
        cl1.setId(1);
        cl1.setNombre("Marvin");
        cl1.setEmail("marvin@gmail.com");
        cl1.setPais("ECU");*/
    }

    public Cliente buscarCliente(String nombre){
        Assert.notNull(nombre,"Error: Campo nombre vacio");
        return clientes.get(nombre);
    }

}

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

        Cliente cl2 = new Cliente();
        cl2.setId(2);
        cl2.setNombre("Anthony");
        cl2.setEmail("anthony@gmail.com");
        cl2.setPais("COL");

        clientes.put(cl2.getNombre(), cl2);

    }

    public Cliente buscarCliente(String nombre, int id){
        Assert.notNull(nombre,"Error: Campo nombre vacio");

        for(Cliente cl : clientes.values()){
            if(cl.getId() == id && cl.getNombre() == nombre){
                System.out.println(cl);
                return cl;
            }
        }
        return clientes.get(nombre);
    }
}

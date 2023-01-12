package soap.Services;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRegistration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


//Habilita el soporte para web services SOAP en una aplicación.
@EnableWs
//Se utiliza para indicar que una clase es una clase de configuración.
@Configuration
public class WsConfiguration extends WsConfigurerAdapter {
    /*
    WsConfigurerAdapter es una clase en Spring Framework que proporciona una forma conveniente
    de personalizar la configuración de los servicios web SOAP en una aplicación.

    @Bean es una anotacion que se utiliza para crear beans de configuración para cualquier clase
     Es un objeto que es manejado por el contenedor de Spring.
     El contenedor se encarga de la creación, configuración y gestión de vida del bean.
    */

    //ServletRegistrationBean proporciona una forma  de registrar un Servlet en una aplicación web basada en Spring

    /*
    * MessageDispatcherServlet es una clase en Spring Framework que proporciona
    * soporte para servicios web SOAP en una aplicación web basada en Spring.
    * Esta clase extiende de HttpServlet y se encarga de recibir las solicitudes SOAP,
    * delegar el procesamiento a los controladores apropiados y enviar las respuestas SOAP
    * */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext appContext){
        /*ApplicationContext Proporciona una forma de acceder a los beans y configuraciones.
        Es la interfaz principal para acceder a un contenedor de Spring y se utiliza para obtener instancias de beans,
        registrar eventos y configuraciones, y para obtener información sobre el entorno de la aplicación.
         */
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(appContext);

        //indica si las ubicaciones WSDL deben ser transformadas durante la ejecución.
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    //XsdSchema proporciona una forma de acceder a un esquema XSD
    @Bean
    public XsdSchema clienteSchema() {
        return new SimpleXsdSchema(new ClassPathResource("cliente.xsd"));
    }
    /*
    DefaultWsdl11Definition Es una clase específica para SOAP y se utiliza para generar archivos WSDL a partir de esquemas XSD
    */
    @Bean(name = "clientes")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema clienteSchema){
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("ClientPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://soap.ws/SOAPWebServices");
        wsdl11Definition.setSchema(clienteSchema);

        return wsdl11Definition;
    }
}

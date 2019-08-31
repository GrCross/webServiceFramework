package apps;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 *
 */
public class Controller
{
    public static Service service;


    public static Service getService(){
        return service;
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, IOException {
        service = new Service();

        service.initialize("apps.WebServiceHello");
        service.listen();
    }
}

package apps;


import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 *
 */
public class Controler 
{
    public static Service service;


    public static Service getService(){
        return service;
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        service = new Service();

        service.initialize("apps.WebServiceHello");
    }
}

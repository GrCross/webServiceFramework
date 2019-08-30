package apps;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class Service{

    Map<String,Handler> URLHandleList = new HashMap<>();

    public void listen(){

    }

    public void initialize(String path) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {


            Class<?> c = Class.forName(path);

            for (Method m: c.getMethods()) {
                System.out.println(m.getAnnotations().toString());
                if(m.isAnnotationPresent(Web.class)){
                    Handler handler = new StaticMethodHandler(m);
                    Web an = m.getAnnotation(Web.class);
                    URLHandleList.put("apps/"+an.value(),handler);
                    System.out.println("invoking method "+c.getName()+"."+m.getName()+"");
                    System.out.println(m.invoke(null,null));
                }
            }

    }

}
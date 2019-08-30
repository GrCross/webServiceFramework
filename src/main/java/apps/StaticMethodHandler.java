package apps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StaticMethodHandler implements Handler {

    Method m;
    public StaticMethodHandler(Method m){
    this.m = m;
    }

    @Override
    public String processor() {
        try {
            return (String) m.invoke(null , null);
        } catch (Exception e) {
        }
        return null;
    }
}

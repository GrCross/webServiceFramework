package apps;

import java.lang.reflect.Method;

public class StaticMethodHandler implements Handler {

    Method m;
    public StaticMethodHandler(Method m){
    this.m = m;
    }

    @Override
    public Object processor() {
        try {
            return m.invoke(null , null);
        } catch (Exception e) {
            return null;
        }
    }
}

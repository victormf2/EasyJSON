package my.gambi.utils;

/**
 *
 * @author Victor Machado
 */
import java.lang.reflect.Method;
import static java.util.Locale.ENGLISH;

public class MethodUtils {

    public static String setterName(String fieldName) {
        return "set" + capitalize(fieldName);
    }
    
    public static String getterName(String fieldName) {
        return "get" + capitalize(fieldName);
    }
    
    public static boolean isSetter(Method method) {
        return method.getName().startsWith("set") && method.getParameterTypes().length == 1;
    }
    
    public static Method setter(String fieldName, Class fieldClass, Class clazz) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(setterName(fieldName), fieldClass);
    }
    
    public static Method getter(String fieldName, Class clazz) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(getterName(fieldName));
    }
    
    private static String capitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        return name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
    }
}

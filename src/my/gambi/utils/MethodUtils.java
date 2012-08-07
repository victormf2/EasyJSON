package my.gambi.utils;

/**
 *
 * @author Victor Machado
 */
import static java.util.Locale.ENGLISH;

public class MethodUtils {

    public static String setterName(String propertyName) {
        return "set" + capitalize(propertyName);
    }
    
    private static String capitalize(String name) {
        if (name == null || name.length() == 0) {
            return name;
        }
        return name.substring(0, 1).toUpperCase(ENGLISH) + name.substring(1);
    }
}

package my.gambi.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Victor Machado
 */
public class ArrayUtils {

    public static String toString(Object object) {
        if (object == null) {
            return null;
        }
        if (object.getClass().isArray()) {
            StringBuilder stringBuilder = new StringBuilder("[");
            Object[] array = (Object[]) object;
            if (array.length > 0) {
                int lastIndex = array.length - 1;
                for (int i = 0; i < lastIndex; i++) {
                    stringBuilder.append(toString(array[i])).append(", ");
                }
                stringBuilder.append(toString(array[lastIndex]));
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
        if (object instanceof Collection) {
            StringBuilder stringBuilder = new StringBuilder("[");
            Collection collection = (Collection) object;
            if (!collection.isEmpty()) {
                int lastIndex = collection.size() - 1;
                int i = 0;
                Iterator it = collection.iterator();
                while (i < lastIndex) {
                    stringBuilder.append(toString(it.next())).append(", ");
                    i++;
                }
                stringBuilder.append(toString(it.next()));
            }
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
        if (object instanceof CharSequence) {
            return new StringBuilder("\"").append(object.toString()).append("\"").toString();
        }
        return object.toString();
    }

    public static boolean isCollection(Type type) {
        return type instanceof ParameterizedType
                && Collection.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType());
    }
}

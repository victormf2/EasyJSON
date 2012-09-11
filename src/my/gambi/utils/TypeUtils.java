package my.gambi.utils;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Victor Machado
 */
public class TypeUtils {

    public static boolean isMap(Type type) {
        return type instanceof ParameterizedType
                && (Map.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType()));
    }

    public static boolean isArray(Type type) {
        return (type instanceof Class && ((Class) type).isArray())
                || type instanceof GenericArrayType;
    }

    public static boolean isCollection(Type type) {
        return type instanceof ParameterizedType
                && (Collection.class.isAssignableFrom((Class) ((ParameterizedType) type).getRawType()));
    }

    public static boolean isBasic(Type type) {
        if (!(type instanceof Class)) {
            return false;
        }
        Class c = (Class) type;
        return c == String.class || c == Integer.class || c == int.class || c == BigDecimal.class
                || c == BigInteger.class || c == Boolean.class || c == boolean.class
                || c == Byte.class || c == byte.class || c == char.class || c == Character.class
                || c == Date.class || c == Double.class || c == double.class || c == Float.class
                || c == float.class || c == Long.class || c == long.class || c == Short.class
                || c == short.class;
    }

    public static boolean isEnum(Type type) {
        return type instanceof Class && ((Class) type).isEnum();
    }
    
    public static Class getClass(Type type) {
        
        if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            return Array.newInstance(getClass(genericArrayType.getGenericComponentType()), 0).getClass();
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        return (Class) type;
    }
}

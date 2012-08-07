package my.gambi.json;

import java.text.ParseException;
import my.gambi.utils.DateUtils;
import java.util.Date;
import java.math.BigDecimal;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.lang.reflect.Array;
import org.json.JSONArray;
import java.lang.reflect.Field;
import org.json.JSONObject;
import java.beans.*;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import static my.gambi.utils.MethodUtils.*;

/**
 *
 * @author Victor Machado
 */
public class JSON {

    public static Object objectFromJSON(String jsonString, Class clazz) throws Exception {

        return objectFromJSON(new JSONObject(jsonString), clazz);
        
    }

    public static Object objectFromJSON(JSONObject jsonObject, Class clazz) throws Exception {
        
        Object object = clazz.newInstance();

        for (String propertyName : JSONObject.getNames(jsonObject)) {

            Method setter = null;
            try {
                setter = new PropertyDescriptor(propertyName, clazz, null, setterName(propertyName))
                        .getWriteMethod();
            } catch (IntrospectionException introspectionException) {
                //Do nothing because of direct property setter
            }
            Object value = jsonObject.get(propertyName);
            setProperty(object, value, setter, propertyName);
        }

        return object;
    }

    public static Object arrayFromJSON(JSONArray jsonArray, Class type, Class elementType) throws
            Exception {
        
        int length = jsonArray.length();
        if (type.isArray()) {
            Object array = Array.newInstance(type.getComponentType(), length);
            for (int i = 0; i < length; i++) {
                Array.set(array, i, propertyValue(jsonArray.get(i), type.getComponentType()));
            }
            return array;
        }

        if (Collection.class.isAssignableFrom(type)) {
            Collection collection = null;
            if (!type.isInterface() && !Modifier.isAbstract(type.getModifiers())) {
                collection = (Collection) type.newInstance();
            } else if (List.class.isAssignableFrom(type)) {
                collection = new ArrayList();
            } else if (Set.class.isAssignableFrom(type)) {
                collection = new HashSet();
            }
            for (int i = 0; i < length; i++) {
                collection.add(propertyValue(jsonArray.get(i), elementType));
            }
            return collection;
        }
        return null;
    }

    private static void setProperty(Object object, Object value, Method setter,
            String propertyName) throws Exception {

        Class type;
        if (setter != null) {
            type = setter.getParameterTypes()[0];
            if (value instanceof JSONArray) {
                setter.invoke(object, arrayFromJSON((JSONArray) value,type,
                        elementType(setter, null)));
            } else {
                setter.invoke(object, propertyValue(value, type));
            }
            return;
        }
        Field field = object.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);
        type = field.getType();
        if (value instanceof JSONArray) {
            field.set(object, arrayFromJSON((JSONArray) value,type, elementType(null, field)));
            return;
        }
        field.set(object, propertyValue(value, type));
    }

    private static Object propertyValue(Object value, Class type) throws Exception {

        Object propertyValue = value;
        if (propertyValue != null) {
            if (propertyValue instanceof JSONObject) {
                propertyValue = objectFromJSON((JSONObject) propertyValue, type);
            } else {
                propertyValue = parse(propertyValue, type);
            }
        }
        return propertyValue;
    }

    private static Object parse(Object value, Class type) throws ParseException {
        if (value.equals(null)) {
            return null;
        }
        if (type.isInstance(value) || type.isAssignableFrom(boolean.class)) {
            return value;
        }
        if (type.isAssignableFrom(Long.class) || type.isAssignableFrom(long.class)) {
            return parseLong(value);
        }
        if (type.isAssignableFrom(Float.class) || type.isAssignableFrom(float.class)) {
            return parseFloat(value);
        }
        if (type.isAssignableFrom(BigDecimal.class)) {
            return parseBigDecimal(value);
        }
        if (type.isAssignableFrom(Date.class)) {
            return parseDate(value);
        }
        throw new IllegalArgumentException(
                String.format("Parsing %s as %s is not possible", value.getClass(), type)
                );
    }

    private static Object parseLong(Object value) {

        if (value instanceof String) {
            return Long.valueOf(value.toString());
        }
        if (value instanceof Double) {
            return ((Double) value).longValue();
        }
        return ((Integer) value).longValue();
    }

    private static Object parseFloat(Object value) {

        if (value instanceof String) {
            return Float.valueOf(value.toString());
        }
        if (value instanceof Double) {
            return ((Double) value).floatValue();
        }
        if (value instanceof Long) {
            return ((Long) value).floatValue();
        }
        return ((Integer) value).floatValue();
    }

    private static Object parseBigDecimal(Object value) {

        if (value instanceof String) {
            return new BigDecimal((String) value);
        }
        if (value instanceof Double) {
            return new BigDecimal((Double) value);
        }
        if (value instanceof Long) {
            return new BigDecimal((Long) value);
        }
        return new BigDecimal((Integer) value);
    }

    private static Object parseDate(Object value) throws ParseException {
        return DateUtils.parse((String) value);
    }

    private static Class elementType(Method setter, Field field) {
        Type genericParameterType;
        if (setter != null) {
            genericParameterType = setter.getGenericParameterTypes()[0];
        } else {
            genericParameterType = field.getGenericType();
        }
        if (genericParameterType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericParameterType;
            return (Class) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }
}

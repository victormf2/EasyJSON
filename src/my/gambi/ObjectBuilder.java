package my.gambi;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import static my.gambi.utils.MethodUtils.*;

/**
 *
 * @author Victor Machado
 */
public class ObjectBuilder {

    private Object object;

    public ObjectBuilder(final Class clazz) throws InstantiationException, IllegalAccessException {
        object = clazz.newInstance();
    }

    public Object getObject() {
        return object;
    }

    public void set(final String fieldName, final Object value) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        try {
            Method setter = new PropertyDescriptor(fieldName, object.getClass(), null,
                    setterName(fieldName)).getWriteMethod();
            setter.setAccessible(true);
            setter.invoke(object, value);
        } catch (IntrospectionException ex) {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        }
    }

    public Type getFieldType(final String fieldName) throws NoSuchFieldException {
        
        try {
            Method setter = new PropertyDescriptor(fieldName, object.getClass(), null,
                    setterName(fieldName)).getWriteMethod();
            return setter.getGenericParameterTypes()[0];
        } catch (IntrospectionException ex) {
            Field field = object.getClass().getDeclaredField(fieldName);
            return field.getGenericType();
        }
    }
}

package my.gambi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import my.gambi.annotation.JSONField;
import my.gambi.utils.Intrusiveness;
import static my.gambi.utils.MethodUtils.*;

/**
 *
 * @author Victor Machado
 */
public class ObjectBuilder {

    private Class clazz;
    private Object object;
    private boolean fieldIntrusive;
    private boolean methodIntrusive;
    private Map<String, Object> jsonFieldMap;

    public ObjectBuilder(final Class clazz) throws Exception {
        this.clazz = clazz;
        jsonFieldMap = new HashMap<>();
        putAnnotatedSettersInJsonFieldMap(clazz);
        putAnnotatedFieldsInJsonFieldMap(clazz);
    }

    public boolean isFieldIntrusive() {
        return fieldIntrusive;
    }

    public void setFieldIntrusive(boolean fieldIntrusive) {
        this.fieldIntrusive = fieldIntrusive;
    }

    public boolean isMethodIntrusive() {
        return methodIntrusive;
    }

    public void setMethodIntrusive(boolean methodIntrusive) {
        this.methodIntrusive = methodIntrusive;
    }

    public void newInstance() throws Exception {
        object = newInstance(clazz);
    }

    private Object newInstance(Class clazz) throws Exception {
        Class enclosingClass = clazz.getEnclosingClass();
        if (enclosingClass == null) {
            return clazz.newInstance();
        }
        Constructor constructor = clazz.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        Object enclosingInstance = newInstance(enclosingClass);
        return constructor.newInstance(enclosingInstance);
    }

    public Object getObject() {
        return object;
    }

    public void set(final String fieldName, final Object value) throws Exception {

        Object target = jsonFieldMap.get(fieldName);
        if (target == null) {
            try {
                target = setter(fieldName, value, clazz);
            } catch (NoSuchMethodException exception) {
                target = clazz.getDeclaredField(fieldName);
            }
        }

        if (target instanceof Method) {
            setWithMethod((Method) target, value);
        } else {
            setWithField((Field) target, value);
        }
    }

    private void setWithMethod(Method method, Object value) throws Exception {
        if (isMethodIntrusive(method)) {
            method.setAccessible(true);
        }
        method.invoke(object, value);
    }

    private void setWithField(Field field, Object value) throws Exception {
        if (isFieldIntrusive(field)) {
            field.setAccessible(true);
        }
        if (field.isAccessible()) {
            field.set(object, value);
        } else {
            setWithMethod(setter(field.getName(), value, clazz), value);
        }
    }

    public Type getFieldType(final String fieldName) throws Exception {

        Object target = jsonFieldMap.get(fieldName);
        if (target == null) {
            try {
                target = getter(fieldName, clazz);
            } catch (NoSuchMethodException exception) {
                target = clazz.getDeclaredField(fieldName);
            }
        }
        if (target instanceof Method) {
            return typeFromMethod((Method) target);
        }
        return typeFromField((Field) target);
    }

    private Type typeFromMethod(Method method) {
        if (methodIntrusive) {
            method.setAccessible(true);
        }
        if (isSetter(method)) {
            return method.getGenericParameterTypes()[0];
        }
        return method.getGenericReturnType();
    }

    private Type typeFromField(Field field) throws NoSuchMethodException {
        if (fieldIntrusive) {
            field.setAccessible(true);
        }
        if (field.isAccessible()) {
            return field.getGenericType();
        }
        return typeFromMethod(getter(field.getName(), clazz));
    }

    private void putAnnotatedSettersInJsonFieldMap(final Class clazz) throws SecurityException {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            JSONField jsonField = method.getAnnotation(JSONField.class);
            if (jsonField != null && isSetter(method)) {
                jsonFieldMap.put(jsonField.value(), method);
            }
        }
    }

    private void putAnnotatedFieldsInJsonFieldMap(final Class clazz) throws SecurityException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            JSONField jsonField = field.getAnnotation(JSONField.class);
            if (jsonField != null) {
                jsonFieldMap.put(jsonField.value(), field);
            }
        }
    }

    private boolean isFieldIntrusive(Field field) {
        Boolean intrusive = Intrusiveness.isFieldIntrusive(field);
        if (intrusive != null) {
            return intrusive;
        }
        intrusive = Intrusiveness.isFieldIntrusive(clazz);
        if (intrusive != null) {
            return intrusive;
        }
        return fieldIntrusive;
    }

    private boolean isMethodIntrusive(Method method) {
        Boolean intrusive = Intrusiveness.isMethodIntrusive(method);
        if (intrusive != null) {
            return intrusive;
        }
        intrusive = Intrusiveness.isMethodIntrusive(clazz);
        if (intrusive != null) {
            return intrusive;
        }
        return methodIntrusive;
    }
}

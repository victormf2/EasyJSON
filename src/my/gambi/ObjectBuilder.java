package my.gambi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import my.gambi.annotation.JSONField;
import static my.gambi.utils.MethodUtils.*;
import my.gambi.utils.TypeUtils;

/**
 *
 * @author Victor Machado
 */
public class ObjectBuilder {

    private Class clazz;
    private Object object;
    private MemberAccessControl memberAccessControl;
    private Map<String, Object> jsonFieldMap;

    public ObjectBuilder(final Class clazz) throws Exception {
        this.clazz = clazz;
        jsonFieldMap = new HashMap<>();
        putAnnotatedSettersInJsonFieldMap(clazz);
        putAnnotatedFieldsInJsonFieldMap(clazz);
    }
    
    public void setMemberAccessControl(Boolean fieldIntrusive, Boolean methodIntrusive) {
        if (memberAccessControl == null) {
            memberAccessControl = new MemberAccessControl();
        }
        if (fieldIntrusive != null) {
            memberAccessControl.setFieldIntrusive(fieldIntrusive);
        }
        if (methodIntrusive != null) {
            memberAccessControl.setMethodIntrusive(methodIntrusive);
        }
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

    public void set(final String fieldName, final Object fieldValue, final Type fieldType) throws Exception {

        Object target = jsonFieldMap.get(fieldName);
        Class fieldClass = TypeUtils.getClass(fieldType);
        if (target == null) {
            try {
                target = setter(fieldName, fieldClass, clazz);
            } catch (NoSuchMethodException exception) {
                target = clazz.getDeclaredField(fieldName);
            }
        }

        if (target instanceof Method) {
            setWithMethod((Method) target, fieldValue);
        } else {
            setWithField((Field) target, fieldValue, fieldClass);
        }
    }

    private void setWithMethod(Method method, Object fieldValue) throws Exception {
        if (memberAccessControl.isAccessible(method)) {
            method.setAccessible(true);
        }
        method.invoke(object, fieldValue);
    }

    private void setWithField(Field field, Object fieldValue, Class fieldClass) throws Exception {
        if (memberAccessControl.isAccessible(field)) {
            field.setAccessible(true);
        }
        if (field.isAccessible()) {
            field.set(object, fieldValue);
        } else {
            setWithMethod(setter(field.getName(), fieldClass, clazz), fieldValue);
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
        if (memberAccessControl.isAccessible(method)) {
            method.setAccessible(true);
        }
        if (isSetter(method)) {
            return method.getGenericParameterTypes()[0];
        }
        return method.getGenericReturnType();
    }

    private Type typeFromField(Field field) throws NoSuchMethodException {
        if (memberAccessControl.isAccessible(field)) {
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
}

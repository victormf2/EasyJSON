package my.gambi;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import my.gambi.annotation.FieldIntrusive;
import my.gambi.annotation.MethodIntrusive;

/**
 *
 * @author Victor Machado
 */
public class MemberAccessControl {

    private boolean fieldIntrusive;
    private boolean methodIntrusive;

    public void setFieldIntrusive(boolean fieldIntrusive) {
        this.fieldIntrusive = fieldIntrusive;
    }

    public void setMethodIntrusive(boolean methodIntrusive) {
        this.methodIntrusive = methodIntrusive;
    }

    public boolean isAccessible(AccessibleObject accessibleObject) {
        if (accessibleObject instanceof Field) {
            Field field = (Field) accessibleObject;
            return isFieldIntrusive(field) || Modifier.isPublic(field.getModifiers());
        }
        if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            return isMethodIntrusive(method) || Modifier.isPublic(method.getModifiers());
        }
        return false;
    }

    private boolean isFieldIntrusive(Field field) {
        Boolean intrusive = MemberAccessUtils.isFieldIntrusiveByAnnotation(field);
        if (intrusive != null) {
            return intrusive;
        }
        intrusive = MemberAccessUtils.isFieldIntrusiveByAnnotation(field.getDeclaringClass());
        if (intrusive != null) {
            return intrusive;
        }
        return fieldIntrusive;
    }

    private boolean isMethodIntrusive(Method method) {
        Boolean intrusive = MemberAccessUtils.isMethodIntrusiveByAnnotation(method);
        if (intrusive != null) {
            return intrusive;
        }
        intrusive = MemberAccessUtils.isMethodIntrusiveByAnnotation(method.getDeclaringClass());
        if (intrusive != null) {
            return intrusive;
        }
        return methodIntrusive;
    }

    static class MemberAccessUtils {

        static Boolean isFieldIntrusiveByAnnotation(AnnotatedElement annotatedElement) {
            FieldIntrusive fieldIntrusive = annotatedElement.getAnnotation(FieldIntrusive.class);
            if (fieldIntrusive != null) {
                return fieldIntrusive.value();
            }
            return null;
        }

        static Boolean isMethodIntrusiveByAnnotation(AnnotatedElement annotatedElement) {
            MethodIntrusive methodIntrusive = annotatedElement.getAnnotation(MethodIntrusive.class);
            if (methodIntrusive != null) {
                return methodIntrusive.value();
            }
            return null;
        }
    }
}

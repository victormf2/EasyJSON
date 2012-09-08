package my.gambi.utils;

import java.lang.reflect.AnnotatedElement;
import my.gambi.annotation.FieldIntrusive;
import my.gambi.annotation.MethodIntrusive;

/**
 *
 * @author Victor Machado
 */
public class Intrusiveness {
    
    public static Boolean isFieldIntrusive(AnnotatedElement annotatedElement) {
        FieldIntrusive fieldIntrusive = annotatedElement.getAnnotation(FieldIntrusive.class);
        if (fieldIntrusive != null) {
            return fieldIntrusive.value();
        }
        return null;
    }
    
    public static Boolean isMethodIntrusive(AnnotatedElement annotatedElement) {
        MethodIntrusive methodIntrusive = annotatedElement.getAnnotation(MethodIntrusive.class);
        if (methodIntrusive != null) {
            return methodIntrusive.value();
        }
        return null;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.gambi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author Victor Machado
 */
public interface SpecialObjectGenerator {
    
    Object specialGenerate(Field field, Object value);
    Object specialGenerate(Method method, Object value);
}

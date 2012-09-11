package my.gambi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @author Victor Machado
 */
public interface SpecialConversionRule {

    String getSpecialGeneratorId(Field field, Object value);
    String getSpecialGeneratorId(Method method, Object value);

}

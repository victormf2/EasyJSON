package my.gambi;

import java.lang.reflect.Type;

/**
 *
 * @author Victor Machado
 */
public interface ConversionRule {

    Object getConversionParameter(Type type, Object value);
}

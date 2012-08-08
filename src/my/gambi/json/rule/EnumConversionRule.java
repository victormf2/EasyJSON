package my.gambi.json.rule;

import java.lang.reflect.Type;
import my.gambi.ConversionRule;
import static my.gambi.utils.TypeAssertion.isEnum;

/**
 *
 * @author Victor Machado
 */
public class EnumConversionRule implements ConversionRule {

    public Object getConversionParameter(Type type, Object value) {

        return isEnum(type) && value instanceof String ? "EnumGenerator" : null;
    }

}

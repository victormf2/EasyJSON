package my.gambi.json.rule;

import java.lang.reflect.Type;
import my.gambi.ConversionRule;
import static my.gambi.utils.TypeUtils.isEnum;

/**
 *
 * @author Victor Machado
 */
public class EnumConversionRule implements ConversionRule {

    @Override
    public String getGeneratorId(Type type, Object value) {

        return isEnum(type) && value instanceof String ? "EnumGenerator" : null;
    }

}

package my.gambi.json.rule;

import java.lang.reflect.Type;
import my.gambi.ConversionRule;
import static my.gambi.utils.TypeUtils.*;
/**
 *
 * @author Victor Machado
 */
public class BasicConversionRule implements ConversionRule {

    @Override
    public String getGeneratorId(Type type, Object value) {
        return isBasic(type) ? "BasicObjectGenerator" : null;
    }

}

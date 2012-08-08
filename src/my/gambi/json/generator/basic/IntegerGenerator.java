package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.FromJsonObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class IntegerGenerator extends FromJsonObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof Integer) {
            return value;
        }
        if (value instanceof String) {
            return Integer.valueOf((String) value);
        }
        if (value instanceof Double || value instanceof Long) {
            return ((Number) value).intValue();
        }
        throw new ParseException(value, type);
    }

}

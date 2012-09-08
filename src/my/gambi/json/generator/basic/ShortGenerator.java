package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class ShortGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            return Short.valueOf((String) value);
        }
        if (value instanceof Double || value instanceof Integer || value instanceof Long) {
            return ((Number) value).shortValue();
        }
        throw new ParseException(value, type);
    }

}

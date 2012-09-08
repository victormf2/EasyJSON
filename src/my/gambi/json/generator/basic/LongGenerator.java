package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class LongGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof Long) {
            return value;
        }
        if (value instanceof String) {
            return Long.valueOf((String) value);
        }
        if (value instanceof Double) {
            return new Long(((Double) value).longValue());
        }
        if (value instanceof Integer) {
            return new Long((Integer) value);
        }
        throw new ParseException(value, type);
    }

}

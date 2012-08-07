package my.gambi.json.generator;

import java.lang.reflect.Type;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class FloatGenerator implements ObjectGenerator {

    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            return Float.valueOf((String) value);
        }
        if (value instanceof Double) {
            return new Float((Double) value);
        }
        if (value instanceof Integer) {
            return new Float((Integer) value);
        }
        if (value instanceof Long) {
            return new Float((Long) value);
        }
        throw new ParseException(value, type);
    }

}

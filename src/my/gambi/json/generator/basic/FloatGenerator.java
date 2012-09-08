package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class FloatGenerator extends DefaultObjectGenerator {

    @Override
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

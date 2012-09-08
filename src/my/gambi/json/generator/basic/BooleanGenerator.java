package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class BooleanGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof Boolean) {
            return value;
        }
        if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }
        throw new ParseException(value, type);
    }

}

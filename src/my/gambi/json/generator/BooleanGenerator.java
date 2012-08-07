package my.gambi.json.generator;

import java.lang.reflect.Type;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class BooleanGenerator implements ObjectGenerator {

    public Object generate(Type type, Object value) throws ParseException {

        Class typeClass = (Class) type;
        if (typeClass == Boolean.class || typeClass == boolean.class) {
            return value;
        }
        if (typeClass == String.class) {
            return Boolean.parseBoolean((String) value);
        }
        throw new ParseException(value, type);
    }

}

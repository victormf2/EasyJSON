package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class ByteGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            return Byte.valueOf((String) value);
        }
        if (value instanceof Double || value instanceof Integer || value instanceof Long) {
            return new Byte(((Number)value).byteValue());
        }
        throw new ParseException(value, type);
    }

}

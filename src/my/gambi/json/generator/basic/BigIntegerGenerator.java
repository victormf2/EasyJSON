package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import java.math.BigInteger;
import my.gambi.json.FromJsonObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class BigIntegerGenerator extends FromJsonObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            return new BigInteger((String) value);
        }
        if (value instanceof Double || value instanceof Integer || value instanceof Long) {
            return new BigInteger(value.toString());
        }
        throw new ParseException(value, type);
    }

}

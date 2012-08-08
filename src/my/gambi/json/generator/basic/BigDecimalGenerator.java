package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import my.gambi.json.FromJsonObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class BigDecimalGenerator extends FromJsonObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            return new BigDecimal((String) value);
        }
        if (value instanceof Double) {
            return new BigDecimal((Double) value);
        }
        if (value instanceof Integer) {
            return new BigDecimal((Integer) value);
        }
        if (value instanceof Long) {
            return new BigDecimal((Long) value);
        }
        throw new ParseException(value, type);
    }

}

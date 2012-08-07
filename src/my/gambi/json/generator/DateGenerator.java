package my.gambi.json.generator;

import java.lang.reflect.Type;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;
import my.gambi.utils.DateUtils;

/**
 *
 * @author Victor Machado
 */
public class DateGenerator implements ObjectGenerator {

    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            try {
                return DateUtils.parse((String) value);
            } catch (java.text.ParseException ex) {
                throw new ParseException(value, type, ex);
            }
        }
        throw new ParseException(value, type);
    }

}

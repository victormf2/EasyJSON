package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.exception.ParseException;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.utils.DateUtils;

/**
 *
 * @author Victor Machado
 */
public class DateGenerator extends DefaultObjectGenerator {

    @Override
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

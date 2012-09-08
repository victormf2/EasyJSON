package my.gambi.json.generator;

import java.lang.reflect.Type;
import my.gambi.exception.ParseException;
import my.gambi.json.DefaultObjectGenerator;

/**
 *
 * @author Victor Machado
 */
public class EnumGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        try {
            return ((Class) type).getMethod("valueOf", String.class).invoke(null, value);
        } catch (Exception ex) {
            throw new ParseException(value, type, ex);
        }
    }

}

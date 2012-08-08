package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.FromJsonObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class CharacterGenerator extends FromJsonObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            String string = (String) value;
            return string.isEmpty() ? (char) 0 : string.charAt(0);
        }
        throw new ParseException(value, type);
    }

}

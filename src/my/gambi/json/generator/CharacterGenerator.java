package my.gambi.json.generator;

import java.lang.reflect.Type;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class CharacterGenerator implements ObjectGenerator {

    public Object generate(Type type, Object value) throws ParseException {

        if (value instanceof String) {
            String string = (String) value;
            return string.isEmpty() ? (char) 0 : string.charAt(0);
        }
        throw new ParseException(value, type);
    }

}

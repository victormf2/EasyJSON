package my.gambi.json.generator;

import java.lang.reflect.Type;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class StringGenerator implements ObjectGenerator {

    public Object generate(Type type, Object value) throws ParseException {
        return value.toString();
    }

}

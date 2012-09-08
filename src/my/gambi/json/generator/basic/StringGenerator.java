package my.gambi.json.generator.basic;

import java.lang.reflect.Type;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class StringGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {
        return value.toString();
    }

}

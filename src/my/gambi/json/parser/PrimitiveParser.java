package my.gambi.json.parser;

import java.lang.reflect.Type;
import my.gambi.ValueParser;
import my.gambi.exception.ParseException;
import my.gambi.json.JSONObjectGenerator;

/**
 *
 * @author Victor Machado
 */
public class PrimitiveParser implements ValueParser {

    private JSONObjectGenerator objectGenerator = new JSONObjectGenerator();

    public Object parse(final Object value, final Type type) throws ParseException {
        return objectGenerator.generate(type, value);
    }
}

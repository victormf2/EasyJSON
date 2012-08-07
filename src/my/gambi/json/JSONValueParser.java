package my.gambi.json;

import my.gambi.json.parser.JSONArrayParser;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import my.gambi.ValueParser;
import my.gambi.exception.ParseException;
import my.gambi.json.parser.JSONObjectParser;
import my.gambi.json.parser.PrimitiveParser;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Victor Machado
 */
public class JSONValueParser implements ValueParser {

    Map<Class, ValueParser> typeSpecificParsers;

    public JSONValueParser() {
        typeSpecificParsers = new HashMap<Class, ValueParser>();
        ValueParser primitiveParser = new PrimitiveParser();
        typeSpecificParsers.put(String.class, primitiveParser);
        typeSpecificParsers.put(JSONObject.class, new JSONObjectParser());
        typeSpecificParsers.put(JSONArray.class, new JSONArrayParser());
        typeSpecificParsers.put(Boolean.class, primitiveParser);
        typeSpecificParsers.put(Double.class, primitiveParser);
        typeSpecificParsers.put(Integer.class, primitiveParser);
        typeSpecificParsers.put(Long.class, primitiveParser);
    }

    public Object parse(final Object value, final Type type) throws ParseException {
        if (value == null) {
            return null;
        }
        ValueParser parser = typeSpecificParsers.get(value.getClass());
        if (parser == null) {
            return null;
        }
        return parser.parse(value, type);
    }

}

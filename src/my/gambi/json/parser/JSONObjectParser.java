package my.gambi.json.parser;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import my.gambi.ValueParser;
import my.gambi.exception.ParseException;
import my.gambi.json.JSONGambi;
import my.gambi.json.generator.MapGenerator;
import org.json.JSONObject;

/**
 *
 * @author Victor Machado
 */
public class JSONObjectParser implements ValueParser {

    private MapGenerator mapGenerator;

    public JSONObjectParser() {
        mapGenerator = new MapGenerator();
    }

    public Object parse(Object value, Type type) throws ParseException {

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            if (Map.class.isAssignableFrom((Class) parameterizedType.getRawType())) {
                return mapGenerator.generate(type, value);
            }
        }
        try {
            return JSONGambi.fromJSONObject((JSONObject) value, (Class) type);
        } catch (Exception ex) {
            throw new ParseException(value, type, ex);
        }
    }

}

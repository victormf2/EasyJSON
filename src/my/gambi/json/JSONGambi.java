package my.gambi.json;

import java.lang.reflect.Type;
import my.gambi.ObjectBuilder;
import org.json.JSONObject;

/**
 *
 * @author Victor Machado
 */
public class JSONGambi {

    public static final JSONValueParser VALUE_PARSER = new JSONValueParser();

    public static Object fromJson(final String json, final Class clazz) throws Exception {

        JSONObject jsonObject = new JSONObject(json);
        return fromJSONObject(jsonObject, clazz);
    }

    public static Object fromJSONObject(final JSONObject jsonObject, final Class clazz) throws
            Exception {

        ObjectBuilder builder = new ObjectBuilder(clazz);

        String[] fieldNames = JSONObject.getNames(jsonObject);

        if (fieldNames != null) {
            for (String fieldName : fieldNames) {

                Object value = jsonObject.get(fieldName);
                Type type = builder.getFieldType(fieldName);
                builder.set(fieldName, VALUE_PARSER.parse(value, type));
            }
        }
        return builder.getObject();
    }
}

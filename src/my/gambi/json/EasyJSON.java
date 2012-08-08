package my.gambi.json;

import java.lang.reflect.Type;
import java.util.regex.Pattern;
import my.gambi.exception.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Victor Machado
 */
public class EasyJSON {

    private ObjectGeneratorContainer objectGeneratorContainer;

    public EasyJSON() {
        objectGeneratorContainer = new ObjectGeneratorContainer();
    }

    public Object fromJson(String json, Type type) throws Exception {

        if (json == null) {
            return null;
        }
        if (type == null) {
            throw new ParseException(json, type);
        }
        Pattern jsonObjectPattern = Pattern.compile("^[ ]*[{].*[}][ ]*$");
        if (jsonObjectPattern.matcher(json).find()) {
            return objectGeneratorContainer.convert(new JSONObject(json), type);
        }
        Pattern jsonArrayPattern = Pattern.compile("^[ ]*\\[.*\\][ ]*$");
        if (jsonArrayPattern.matcher(json).find()) {
            return objectGeneratorContainer.convert(new JSONArray(json), type);
        }
        return objectGeneratorContainer.convert(json, type);
    }
}

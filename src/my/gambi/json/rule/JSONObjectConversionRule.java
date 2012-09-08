package my.gambi.json.rule;

import java.lang.reflect.Type;
import my.gambi.ConversionRule;
import static my.gambi.utils.TypeAssertion.*;
import org.json.JSONObject;

/**
 *
 * @author Victor Machado
 */
public class JSONObjectConversionRule implements ConversionRule {

    @Override
    public String getGeneratorId(Type type, Object value) {

        if (value instanceof JSONObject) {
            return isMap(type) ? "MapGenerator" : "BeanGenerator";
        }
        return null;
    }
}

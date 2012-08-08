package my.gambi.json.rule;

import java.lang.reflect.Type;
import my.gambi.ConversionRule;
import org.json.JSONObject;
import static  my.gambi.utils.TypeAssertion.*;

/**
 *
 * @author Victor Machado
 */
public class JSONObjectConversionRule implements ConversionRule {

    public Object getConversionParameter(Type type, Object value) {

        if (value instanceof JSONObject) {
            return isMap(type) ? "MapGenerator" : "BeanGenerator";
        }
        return null;
    }

    

}

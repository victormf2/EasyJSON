package my.gambi.json.rule;

import java.lang.reflect.Type;
import my.gambi.ConversionRule;
import org.json.JSONArray;
import static  my.gambi.utils.TypeAssertion.*;

/**
 *
 * @author Victor Machado
 */
public class JSONArrayConversionRule implements ConversionRule {

    public Object getConversionParameter(Type type, Object value) {

        if (value instanceof JSONArray) {
            if (isArray(type)) {
                return "ArrayGenerator";
            }
            if (isCollection(type)) {
                return "CollectionGenerator";
            }
            return null;
        }
        return null;
    }

}

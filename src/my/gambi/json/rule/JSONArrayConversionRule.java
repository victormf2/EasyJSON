package my.gambi.json.rule;

import java.lang.reflect.Type;
import my.gambi.ConversionRule;
import static my.gambi.utils.TypeUtils.*;
import org.json.JSONArray;

/**
 *
 * @author Victor Machado
 */
public class JSONArrayConversionRule implements ConversionRule {

    @Override
    public String getGeneratorId(Type type, Object value) {

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

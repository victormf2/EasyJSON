package my.gambi.json.generator;

import java.lang.reflect.Type;
import my.gambi.ObjectBuilder;
import my.gambi.json.FromJsonObjectGenerator;
import my.gambi.exception.ParseException;
import org.json.JSONObject;

/**
 *
 * @author Victor Machado
 */
public class BeanGenerator extends FromJsonObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        try {
            JSONObject jsonObject = (JSONObject) value;
            Class clazz = (Class) type;

            ObjectBuilder builder = new ObjectBuilder(clazz);

            String[] fieldNames = JSONObject.getNames(jsonObject);

            if (fieldNames != null) {
                for (String fieldName : fieldNames) {

                    Object fieldValue = jsonObject.get(fieldName);
                    Type fieldType = builder.getFieldType(fieldName);
                    builder.set(fieldName, container.convert(fieldValue, fieldType));
                }
            }
            return builder.getObject();
        } catch (Exception exception) {
            throw new ParseException(value, type, exception);
        }
    }

}

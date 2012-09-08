package my.gambi.json.generator;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import my.gambi.ObjectBuilder;
import my.gambi.exception.ParseException;
import my.gambi.json.DefaultObjectGenerator;
import org.json.JSONObject;

/**
 * 
 * @author Victor Machado
 */
public class BeanGenerator extends DefaultObjectGenerator {

    private boolean fieldIntrusive;
    private boolean methodIntrusive;
    private Map<Class, ObjectBuilder> objectBuilders;

    public BeanGenerator() {
        objectBuilders = new HashMap<>();
    }
    
    @Override
    public Object generate(Type type, Object value) throws ParseException {

        try {
            JSONObject jsonObject = (JSONObject) value;
            Class clazz = (Class) type;

            ObjectBuilder builder = getObjectBuilder(clazz);
            builder.newInstance();
            
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

    public void setFieldIntrusive(boolean fieldIntrusive) {
        this.fieldIntrusive = fieldIntrusive;
    }

    public void setMethodIntrusive(boolean methodIntrusive) {
        this.methodIntrusive = methodIntrusive;
    }
    
    private ObjectBuilder getObjectBuilder(Class clazz) throws Exception {
        ObjectBuilder builder = objectBuilders.get(clazz);
        if (builder == null) {
            builder = new ObjectBuilder(clazz);
            builder.setFieldIntrusive(fieldIntrusive);
            builder.setMethodIntrusive(methodIntrusive);
            objectBuilders.put(clazz, builder);
        }
        return builder;
    }

}

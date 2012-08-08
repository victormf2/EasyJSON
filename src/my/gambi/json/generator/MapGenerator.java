package my.gambi.json.generator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import my.gambi.json.FromJsonObjectGenerator;
import my.gambi.exception.ParseException;
import org.json.JSONObject;

/**
 *
 * @author Victor Machado
 */
public class MapGenerator extends FromJsonObjectGenerator {
    
    @Override
    public Object generate(Type type, Object value) throws ParseException {

        try {
            JSONObject jsonObject = (JSONObject) value;
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class mapClass = (Class) parameterizedType.getRawType();
            Type keyType = parameterizedType.getActualTypeArguments()[0];
            Type valueType = parameterizedType.getActualTypeArguments()[1];
            
            Map map = getMapByClass(mapClass);
            String[] fieldNames = JSONObject.getNames(jsonObject);
            if (fieldNames != null) {
                for (String key : fieldNames) {
                    map.put(container.convert(key, keyType),
                            container.convert(jsonObject.get(key), valueType));
                }
            }
            return map;
        } catch (Exception ex) {
            throw new ParseException(ex);
        }
    }

    private Map getMapByClass(Class mapClass) throws IllegalAccessException,
            InstantiationException {

        Map map = null;
        int modifiers = mapClass.getModifiers();
        if (!Modifier.isInterface(modifiers) && !Modifier.isAbstract(modifiers)) {
            map = (Map) mapClass.newInstance();
        } else {
            map = new HashMap();
        }
        return map;
    }

}

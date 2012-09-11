package my.gambi.json.generator;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import my.gambi.exception.ParseException;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.utils.TypeUtils;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Victor Machado
 */
public class ArrayGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        Type componentType;
        if (type instanceof GenericArrayType) {
            componentType = ((GenericArrayType) type).getGenericComponentType();
        } else {
            componentType = ((Class) type).getComponentType();
        }

        JSONArray jsonArray = (JSONArray) value;
        int length = jsonArray.length();
        Object[] array = (Object[]) Array.newInstance(TypeUtils.getClass(componentType), length);
        for (int i = 0; i < length; i++) {
            try {
                array[i] = container.convert(jsonArray.get(i), componentType);
            } catch (JSONException ex) {
                throw new ParseException(ex);
            }
        }
        return array;
    }
}

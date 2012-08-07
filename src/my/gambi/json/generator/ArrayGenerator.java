package my.gambi.json.generator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import static my.gambi.json.JSONGambi.VALUE_PARSER;

/**
 *
 * @author Victor Machado
 */
public class ArrayGenerator implements ObjectGenerator {

    public Object generate(Type type, Object value) throws ParseException {

        Type componentType = type;
        if (type instanceof GenericArrayType) {
            componentType = ((GenericArrayType) type).getGenericComponentType();
        }

        JSONArray jsonArray = (JSONArray) value;
        int length = jsonArray.length();
        Object[] array = (Object[]) Array.newInstance(discoverType(componentType), length);
        for (int i = 0; i < length; i++) {
            try {
                array[i] = VALUE_PARSER.parse(jsonArray.get(i), componentType);
            } catch (JSONException ex) {
                throw new ParseException(ex);
            }
        }
        return array;
    }

    public Class discoverType(Type componentType) {
        
        if (componentType instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) componentType;
            return Array.newInstance(
                    (Class) discoverType(genericArrayType.getGenericComponentType()), 0).getClass();
        }
        if (componentType instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) componentType).getRawType();
        }
        return (Class) componentType;
    }
}
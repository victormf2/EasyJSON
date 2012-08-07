package my.gambi.json.parser;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import my.gambi.ValueParser;
import my.gambi.exception.ParseException;
import my.gambi.json.generator.ArrayGenerator;
import my.gambi.json.generator.CollectionGenerator;

/**
 *
 * @author Victor Machado
 */
public class JSONArrayParser implements ValueParser {

    private ArrayGenerator arrayGenerator;
    private CollectionGenerator collectionGenerator;

    public JSONArrayParser() {
        arrayGenerator = new ArrayGenerator();
        collectionGenerator = new CollectionGenerator();
    }

    public Object parse(Object value, Type type) throws ParseException {

        if (type instanceof Class) {
            return arrayGenerator.generate(type, value);
        }
        if (type instanceof ParameterizedType) {
            return collectionGenerator.generate(type, value);
        }
        if (type instanceof GenericArrayType) {
            return arrayGenerator.generate(type, value);
        }
        throw new ParseException(value, type);
    }
}

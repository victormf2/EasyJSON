package my.gambi.json;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;
import my.gambi.json.generator.BigDecimalGenerator;
import my.gambi.json.generator.BigIntegerGenerator;
import my.gambi.json.generator.BooleanGenerator;
import my.gambi.json.generator.ByteGenerator;
import my.gambi.json.generator.CharacterGenerator;
import my.gambi.json.generator.DateGenerator;
import my.gambi.json.generator.DoubleGenerator;
import my.gambi.json.generator.FloatGenerator;
import my.gambi.json.generator.IntegerGenerator;
import my.gambi.json.generator.LongGenerator;
import my.gambi.json.generator.ShortGenerator;
import my.gambi.json.generator.StringGenerator;

/**
 *
 * @author Victor Machado
 */
public class JSONObjectGenerator implements ObjectGenerator {

    private Map<Class, ObjectGenerator> typeSpecificGenerators;

    public JSONObjectGenerator() {
        typeSpecificGenerators = new HashMap<Class, ObjectGenerator>();
        typeSpecificGenerators.put(String.class, new StringGenerator());
        typeSpecificGenerators.put(boolean.class, new BooleanGenerator());
        typeSpecificGenerators.put(Boolean.class, new BooleanGenerator());
        typeSpecificGenerators.put(BigDecimal.class, new BigDecimalGenerator());
        typeSpecificGenerators.put(BigInteger.class, new BigIntegerGenerator());
        typeSpecificGenerators.put(Byte.class, new ByteGenerator());
        typeSpecificGenerators.put(byte.class, new ByteGenerator());
        typeSpecificGenerators.put(Character.class, new CharacterGenerator());
        typeSpecificGenerators.put(char.class, new CharacterGenerator());
        typeSpecificGenerators.put(Date.class, new DateGenerator());
        typeSpecificGenerators.put(Double.class, new DoubleGenerator());
        typeSpecificGenerators.put(double.class, new DoubleGenerator());
        typeSpecificGenerators.put(Float.class, new FloatGenerator());
        typeSpecificGenerators.put(float.class, new FloatGenerator());
        typeSpecificGenerators.put(Integer.class, new IntegerGenerator());
        typeSpecificGenerators.put(int.class, new IntegerGenerator());
        typeSpecificGenerators.put(Long.class, new LongGenerator());
        typeSpecificGenerators.put(long.class, new LongGenerator());
        typeSpecificGenerators.put(Short.class, new ShortGenerator());
        typeSpecificGenerators.put(short.class, new ShortGenerator());
    }

    public Object generate(Type type, Object value) throws ParseException {
        
        if (value == null) {
            return null;
        }
        ObjectGenerator objectGenerator = typeSpecificGenerators.get((Class) type);
        return objectGenerator.generate(type, value);
    }

}

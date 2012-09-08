package my.gambi.json.generator;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import my.gambi.exception.ParseException;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.json.generator.basic.BigDecimalGenerator;
import my.gambi.json.generator.basic.BigIntegerGenerator;
import my.gambi.json.generator.basic.BooleanGenerator;
import my.gambi.json.generator.basic.ByteGenerator;
import my.gambi.json.generator.basic.CharacterGenerator;
import my.gambi.json.generator.basic.DateGenerator;
import my.gambi.json.generator.basic.DoubleGenerator;
import my.gambi.json.generator.basic.FloatGenerator;
import my.gambi.json.generator.basic.IntegerGenerator;
import my.gambi.json.generator.basic.LongGenerator;
import my.gambi.json.generator.basic.ShortGenerator;
import my.gambi.json.generator.basic.StringGenerator;

/**
 *
 * @author Victor Machado
 */
public class BasicObjectGenerator extends DefaultObjectGenerator {

    private Map<Type, DefaultObjectGenerator> typeSpecificGenerators;

    public BasicObjectGenerator() {
        typeSpecificGenerators = new HashMap<>();
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

    @Override
    public Object generate(Type type, Object value) throws ParseException {
        
        if (value == null) {
            return null;
        }
        DefaultObjectGenerator objectGenerator = typeSpecificGenerators.get(type);
        return objectGenerator.generate(type, value);
    }

}

package my.gambi.json;

import my.gambi.exception.ParseException;
import my.gambi.json.generator.BasicObjectGenerator;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import my.gambi.ConversionRule;
import my.gambi.json.generator.ArrayGenerator;
import my.gambi.json.generator.BeanGenerator;
import my.gambi.json.generator.CollectionGenerator;
import my.gambi.json.generator.EnumGenerator;
import my.gambi.json.generator.MapGenerator;
import my.gambi.json.rule.BasicConversionRule;
import my.gambi.json.rule.EnumConversionRule;
import my.gambi.json.rule.JSONArrayConversionRule;
import my.gambi.json.rule.JSONObjectConversionRule;

/**
 *
 * @author Victor Machado
 */
public final class ObjectGeneratorContainer {

    private Map<String, FromJsonObjectGenerator> generatorsMap;
    private LinkedList<ConversionRule> conversionRules;

    public ObjectGeneratorContainer() {
        generatorsMap = new HashMap<String, FromJsonObjectGenerator>();
        addGenerator("ArrayGenerator", new ArrayGenerator());
        addGenerator("BeanGenerator", new BeanGenerator());
        addGenerator("CollectionGenerator", new CollectionGenerator());
        addGenerator("BasicObjectGenerator", new BasicObjectGenerator());
        addGenerator("MapGenerator", new MapGenerator());
        addGenerator("EnumGenerator", new EnumGenerator());
        conversionRules = new LinkedList<ConversionRule>();
        addConversionRule(new BasicConversionRule());
        addConversionRule(new JSONArrayConversionRule());
        addConversionRule(new JSONObjectConversionRule());
        addConversionRule(new EnumConversionRule());
    }

    public void addGenerator(String id, FromJsonObjectGenerator objectGenerator) {

        if (id == null || generatorsMap.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate key: " + id);
        }
        objectGenerator.setContainer(this);
        generatorsMap.put(id, objectGenerator);
    }

    public void addConversionRule(ConversionRule conversionRule) {
        conversionRules.addFirst(conversionRule);
    }

    public Object convert(Object value, Type type) throws ParseException {
        if (value == null) {
            return null;
        }
        for (ConversionRule conversionRule : conversionRules) {
            Object conversionParam = conversionRule.getConversionParameter(type, value);
            if (conversionParam == null) {
                continue;
            }
            return generatorsMap.get(conversionParam).generate(type, value);
        }
        throw new ParseException(value, type);
    }
}

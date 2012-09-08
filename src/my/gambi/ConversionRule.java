package my.gambi;

import java.lang.reflect.Type;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.json.ObjectGeneratorContainer;

/**
 * A rule which defines which {@link ObjectGenerator} will be used for parsing a value.
 * Almost all basic types, like String, int, boolean and Date already have a {@code ConversionRule} defined, what means
 * there is only need for creating {@code ConversionRules} for complex types. 
 * 
 * @author Victor Machado
 */
public interface ConversionRule {

    /**
     * Returns a {@link DefaultObjectGenerator}'s id based on one or more rules.
     * These rules are made with validations and assertions upon determined type and value.
     * 
     * @param type {@code Type} of the Object that will be generated
     * @param value Value that will be interpreted and parsed by an {@code ObjectGenerator}
     * @return Id String for identifying a {@code DefaultObjectGenerator} in {@link ObjectGeneratorContainer}
     * @see DefaultObjectGenerator
     */
    String getGeneratorId(Type type, Object value);
}

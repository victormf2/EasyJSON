package my.gambi.json;

import java.lang.reflect.Type;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class DefaultObjectGenerator implements ObjectGenerator {

    protected ObjectGeneratorContainer container;

    public ObjectGeneratorContainer getContainer() {
        return container;
    }

    public void setContainer(ObjectGeneratorContainer container) {
        this.container = container;
    }

    @Override
    public Object generate(Type type, Object value) throws ParseException {
        throw new ParseException(value, type,
                new UnsupportedOperationException("DefaultObjectGenerator can't do a thing"));
    }
}

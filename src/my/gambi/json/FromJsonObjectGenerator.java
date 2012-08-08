/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my.gambi.json;

import java.lang.reflect.Type;
import my.gambi.ObjectGenerator;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public class FromJsonObjectGenerator implements ObjectGenerator {

    protected ObjectGeneratorContainer container;

    public ObjectGeneratorContainer getContainer() {
        return container;
    }

    public void setContainer(ObjectGeneratorContainer container) {
        this.container = container;
    }

    public Object generate(Type type, Object value) throws ParseException {
        throw new ParseException(value, type,
                new UnsupportedOperationException("Default ObjectGenerator can't do a thing"));
    }
}

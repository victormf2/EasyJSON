package my.gambi;

import java.lang.reflect.Type;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public interface ObjectGenerator {

    Object generate(Type type, Object value) throws ParseException;
}

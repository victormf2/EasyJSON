package my.gambi;

import java.lang.reflect.Type;
import my.gambi.exception.ParseException;

/**
 *
 * @author Victor Machado
 */
public interface ValueParser {

    Object parse(Object value, Type type) throws ParseException;
}

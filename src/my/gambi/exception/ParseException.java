package my.gambi.exception;

import java.lang.reflect.Type;

/**
 *
 * @author Victor Machado
 */
public class ParseException extends Exception {

    public ParseException(Object value, Type type) {
        this(value, type, null);
    }

    public ParseException(Throwable cause) {
        super("Error ocurred while parsing", cause);
    }

    public ParseException(Object value, Type type, Throwable cause) {
        super(String.format("Parse from %s to %s is not possible", value.getClass(), type),
                cause);
    }

}

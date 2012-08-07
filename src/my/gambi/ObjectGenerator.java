/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

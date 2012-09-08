package my.gambi.json.generator;

import java.util.HashSet;
import java.util.Set;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import my.gambi.json.DefaultObjectGenerator;
import my.gambi.exception.ParseException;
import org.json.JSONArray;

/**
 *
 * @author Victor Machado
 */
public class CollectionGenerator extends DefaultObjectGenerator {

    @Override
    public Object generate(Type type, Object value) throws ParseException {

        try {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Class collectionClass = (Class) parameterizedType.getRawType();
            JSONArray jsonArray = (JSONArray) value;

            Collection collection = getCollectionByClass(collectionClass);
            Type componentType = parameterizedType.getActualTypeArguments()[0];
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                addObjectToCollection(collection, jsonArray.get(i), componentType);
            }
            return collection;
        } catch (Exception exception) {
            throw new ParseException(value, type, exception);
        }
    }

    private Collection getCollectionByClass(Class collectionClass) throws IllegalAccessException,
            InstantiationException {
        
        Collection collection = null;
        int modifiers = collectionClass.getModifiers();
        if (!Modifier.isInterface(modifiers) && !Modifier.isAbstract(modifiers)) {
            collection = (Collection) collectionClass.newInstance();
        } else if (List.class.isAssignableFrom(collectionClass)) {
            collection = new ArrayList();
        } else if (Set.class.isAssignableFrom(collectionClass)) {
            collection = new HashSet();
        }
        return collection;
    }

    private void addObjectToCollection(Collection collection, Object value, Type type)
            throws ParseException {
        collection.add(container.convert(value, type));
    }
}

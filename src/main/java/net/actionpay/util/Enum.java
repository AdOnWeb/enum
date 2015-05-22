package net.actionpay.util;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Artur Khakimov <djion@ya.ru>
 */
public class Enum<T extends Enum> {
    /**
     * @param clazz Enum class
     * @param id    constant
     * @param name  enum name
     */
    protected static void register(Class<? extends Enum> clazz, int id, String name) {
        if (!map.containsKey(clazz))
            map.put(clazz, new HashMap<Object, String>());
        map.get(clazz).put(id, name);
    }

    static Map<Class<?>, Map<Object, String>> map = new HashMap<>();

    /**
     * Enum initializer
     * @param cls Enum class
     * @param value Init Value
     * @param <E> class
     * @return new Enum child of class cls
     */
    public static <E extends Enum> E by(Class<E> cls, Integer value) {
        E result = null;
        if (Enum.class.isAssignableFrom(cls)) {
            try {
                result = (E) (cls.getConstructor().newInstance());
                result.setInternalId(value);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    protected Integer id;
    protected String name;

    public String toString() {
        return this.name;
    }


    protected Enum setInternalId(Integer id) throws UnexpectedValueException {
        if (map.get(getClass()).containsKey(id)) {
            this.id = id;
            this.name = (map.get(getClass())).get(id);
        } else {
            throw new UnexpectedValueException(getClass() + " knows nothing about such id == " + id);
        }

        return this;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Map toMap() {
        return new HashMap<String, Object>() {
            {
                put("id", id);
                put("name", name);
            }
        };
    }


}

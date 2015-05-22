package net.actionpay.util;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Artur Khakimov <djion@ya.ru>
 */
public class Enum<T extends Enum> {

    protected static void register(Class<? extends Enum> clazz, int id, String name) {
        if (!map.containsKey(clazz))
            map.put(clazz,new HashMap<Object, String>());
        map.get(clazz).put(id,name);
    }

    static Map<Class<?>, Map<Object, String>> map = new HashMap<>();

    public static <E extends Enum> E by(Class<E> cls, Integer value){
        E result = null;
            if (Enum.class.isAssignableFrom(cls)) {
                try {
                    result = (E)(cls.getConstructor().newInstance());
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


    /**
     * @return int|string
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * @return string
     */
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

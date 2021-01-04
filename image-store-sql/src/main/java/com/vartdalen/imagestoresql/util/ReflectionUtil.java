package com.vartdalen.imagestoresql.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static <T> T mergeObjects(T destination, T source) throws IllegalAccessException {
        Field[] allFields = destination.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            if (field.get(source) != null) { field.set(destination, field.get(source)); }
        }
        return destination;
    }
}

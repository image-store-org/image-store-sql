package com.vartdalen.imagestoresql.util;

import java.lang.reflect.Field;

public class ReflectionUtil {
    public static <T> T mergeObjects(T destination, T source) throws IllegalAccessException {
        Field[] allFields = destination.getClass().getDeclaredFields();
        for (Field field : allFields) {
            field.setAccessible(true);
            if (!isDefault(field.get(source), field.getType())) { field.set(destination, field.get(source)); }
        }
        return destination;
    }

    private static boolean isDefault(Object object, Class<?> type) {
        if(type == char.class && ((Character) object).charValue() == 0) {
            return true;
        }
        else if (type.isPrimitive() && type != boolean.class && ((Number) object).doubleValue() == 0) {
            return true;
        }
        else if (object == null) {
            return true;
        }
        return false;
    }
}

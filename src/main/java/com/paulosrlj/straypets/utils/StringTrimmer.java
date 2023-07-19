package com.paulosrlj.straypets.utils;

import java.lang.reflect.Field;

public class StringTrimmer {
    public static void trimStrings(Object object) {
        try {
            Class<?> clazz = object.getClass();

            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType().equals(String.class)) {
                    field.setAccessible(true);
                    String value = (String) field.get(object);
                    if (value != null) {
                        String trimmedValue = value.trim();
                        field.set(object, trimmedValue);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

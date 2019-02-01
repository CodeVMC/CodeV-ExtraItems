package net.codevmc.extraItems.item.serialization;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class SerializationHelper {

    public static String serialize(Object o) {
        return "L"+o.getClass().getName()+":"+getSerializeField(o);
    }

    private static List<String> getSerializeField(Object o) {
        List<String> serializeFieldList = new ArrayList<>();
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .peek(field->field.setAccessible(true))
                .filter(field -> hasSerializationAnnotation(field))
                .forEach(field -> {
                    String fieldClassName = field.getType().getName();
                    try {
                        switch (fieldClassName) {
                            case "int":
                                serializeFieldList.add(field.getName()+":I" + field.getInt(o));
                                break;
                            case "long":
                                serializeFieldList.add(field.getName()+":J"+field.getLong(o));
                                break;
                            case "short":
                                serializeFieldList.add(field.getName()+":S"+field.getShort(o));
                                break;
                            case "boolean":
                                serializeFieldList.add(field.getName()+":Z"+field.getBoolean(o));
                                break;
                            case "byte":
                                serializeFieldList.add(field.getName()+":B"+field.getByte("o"));
                                break;
                            case "char":
                                serializeFieldList.add(field.getName()+":C"+field.getChar(o));
                                break;
                            case "double":
                                serializeFieldList.add(field.getName()+":D"+field.getDouble(o));
                                break;
                            case "float":
                                serializeFieldList.add(field.getName()+":F"+field.getFloat(o));
                                break;
                            case "java.lang.String":
                                serializeFieldList.add(field.getName()+":T"+field.get(o));
                                break;
                            default:
                                serializeFieldList.add(field.getName()+":L"+serialize(field.get(o)));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        return serializeFieldList;
    }

    private static boolean hasSerializationAnnotation(Field field) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            if (annotation.annotationType().equals(Serialization.class))
                return true;
        }
        return false;
    }

    public static <T> T  deserialize(String str){
        return null;
    }
}

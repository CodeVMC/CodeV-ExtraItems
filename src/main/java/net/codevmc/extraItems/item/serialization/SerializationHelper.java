package net.codevmc.extraItems.item.serialization;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class SerializationHelper {

    private HashMap<String, Object> map = new HashMap<>();

    public static SerializationHelper helper() {
        return new SerializationHelper();
    }

    public SerializationHelper put(String key, Object o) {
        if (o instanceof ConfigurationSerializable) {

        }
        this.map.put(key, o);
        return this;
    }

    public Map<String, Object> build() {
        return map;
    }

    public static String serialize(Object o) {
        return "L"+o.getClass().getName()+":"+getSerializeField(o);
    }

    private static List<String> getSerializeField(Object o) {
        List<String> serializeFieldList = new ArrayList<>();
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .filter(field -> hasSerializationAnnotation(field))
                .forEach(field -> {
                    field.setAccessible(true);
                    String fieldClassName = field.getType().getName();
                    try {
                        switch (fieldClassName) {
                            case "int":
                                serializeFieldList.add("I" + field.getInt(o));
                                break;
                            case "long":
                                serializeFieldList.add("J"+field.getLong(o));
                                break;
                            case "short":
                                serializeFieldList.add("S"+field.getShort(o));
                                break;
                            case "boolean":
                                serializeFieldList.add("Z"+field.getBoolean(o));
                                break;
                            case "byte":
                                serializeFieldList.add("B"+field.getByte("o"));
                                break;
                            case "char":
                                serializeFieldList.add("C"+field.getChar(o));
                                break;
                            case "double":
                                serializeFieldList.add("D"+field.getDouble(o));
                                break;
                            case "float":
                                serializeFieldList.add("F"+field.getFloat(o));
                                break;
                            case "java.lang.String":
                                serializeFieldList.add("T"+field.get(o));
                                break;
                            default:
                                serializeFieldList.add("L"+serialize(field.get(o)));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        return serializeFieldList;
    }

    private static boolean hasSerializationAnnotation(Field field) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            if (annotation.getClass().equals(Serialization.class))
                return true;
        }
        return false;
    }
}

package net.codevmc.extraItems.item.serialization;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class SerializationHelper {

    public static String serialize(Object o) {
//        StringBuilder sb = new StringBuilder();
//        for(String s : getSerializeField(o))
//            sb.append(s+"¤");
//        sb.substring(0,sb.length()-1);
        return getSerializeField(o).toString();
    }

    private static List<String> getSerializeField(Object o) {
        List<String> serializeFieldList = new ArrayList<>();
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .peek(field -> field.setAccessible(true))
                .filter(field -> hasSerializationAnnotation(field))
                .forEach(field -> {
                    serializeFieldList.add(serializeClass(o, field));
                });
        return serializeFieldList;
    }

    private static String serializeClass(Object o, Field field) {
        Class type = field.getType();
        String fieldClassName = type.getName();
        String fieldName = field.getName();
        String symbolName = getBasicDataTypeSymbol(type);
        if (isCollectionChild(type))
            fieldClassName = "Collection";
        if (isMapChild(type))
            fieldClassName = "Map";
        try {
            Object fieldInstance = field.get(o);
            if (fieldInstance == null) {
                return fieldName + ":" + "N";
            }
            switch (fieldClassName) {

                case "int":
                    return fieldName + ":" + symbolName + field.getInt(o);
                case "long":
                    return fieldName + ":" + symbolName + field.getLong(o);
                case "short":
                    return fieldName + ":" + symbolName + field.getShort(o);
                case "boolean":
                    return fieldName + ":" + symbolName + field.getBoolean(o);
                case "byte":
                    return fieldName + ":" + symbolName + field.getByte("o");
                case "char":
                    return fieldName + ":" + symbolName + field.getChar(o);
                case "double":
                    return fieldName + ":" + symbolName + field.getDouble(o);
                case "float":
                    return fieldName + ":" + symbolName + field.getFloat(o);
                case "java.lang.String":
                    return fieldName + ":" + symbolName + "[" + field.get(o) + "]";
                case "Collection":
                    return fieldName + ":" + symbolName + serializeCollection((Collection) field.get(o));
                case "Map":
                    return fieldName + ":" + symbolName + serializeMap((Map) field.get(o));
                default:
                    return fieldName + ":" + symbolName + serialize(field.get(o));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "null";
    }

    private static String serializeCollection(Collection collection) {
        if (collection.isEmpty())
            return "[]";
        Object[] array = collection.toArray();
        ArrayList<String> list = new ArrayList<>();
        for (Object o1 : array)
            list.add(object2String(o1));
        return list.toString();
    }

    private static String object2String(Object o) {
        Class clazz = o.getClass();
        String name = clazz.getName();
        switch (name) {
            case "int":
            case "short":
            case "long":
            case "boolean":
            case "byte":
            case "char":
            case "double":
            case "float":
                return getBasicDataTypeSymbol(o) + o.toString();
            case "java.lang.String":
                return getBasicDataTypeSymbol(o) + "[" + o.toString() + "]";
            case "Collection":
                return getBasicDataTypeSymbol(o) + serializeCollection((Collection) o);
            case "Map":
                return getBasicDataTypeSymbol(o) + serializeMap((Map) o);
            default:
                return getBasicDataTypeSymbol(o) + serialize(o);
        }
    }

    private static String getBasicDataTypeSymbol(Object o) {
        return getBasicDataTypeSymbol(o.getClass());
    }

    private static String getBasicDataTypeSymbol(Class clazz) {
        String name = clazz.getName();
        if (isCollectionChild(clazz))
            name = "Collection";
        if (isMapChild(clazz))
            name = "Map";
        switch (name) {
            case "int":
                return "I";
            case "long":
                return "J";
            case "short":
                return "S";
            case "boolean":
                return "Z";
            case "byte":
                return "B";
            case "char":
                return "C";
            case "double":
                return "D";
            case "float":
                return "F";
            case "java.lang.String":
                return "T";
            case "Collection":
                return "O"+clazz.getName();
            case "Map":
                return "P"+clazz.getName();
            default:
                return "L" + name;
        }
    }

    private static String serializeMap(Map map) {
        if (map.isEmpty())
            return "";
        ArrayList<String> list = new ArrayList();
        Set<Map.Entry> set = map.entrySet();
        for (Map.Entry entry : set) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            list.add(object2String(key) + ":" + object2String(value));
        }
        return list.toString();
    }

    private static boolean isMapChild(Class type) {
        return Map.class.isAssignableFrom(type);
    }

    private static boolean isCollectionChild(Class clazz) {
        return Collection.class.isAssignableFrom(clazz);
    }

    private static boolean hasSerializationAnnotation(Field field) {
        for (Annotation annotation : field.getDeclaredAnnotations()) {
            if (annotation.annotationType().equals(Serialization.class))
                return true;
        }
        return false;
    }

    public static <T> T deserialize(T t, String str) {
        str = str.trim();
        if (str.isEmpty())
            return t;


        return null;
    }

    private static Map<String, Object> deserialize2Map(String str) {
        HashMap<String, Object> map = new HashMap<>();

        return null;
    }

    private static class Serializer {

        private String string;

        private int index = 0;

        private int lastIndex = 0;

        public Serializer(String string) {
            if (string.startsWith("[") && string.endsWith("]"))
                this.string = string.substring(1, string.length() - 1);
            else throw new RuntimeException("wrong serialized string: " + string);
        }

        public Map.Entry<String, Object> getNextEntry() {
            indexNextComma();
            String piece = string.substring(lastIndex, index).trim();
            String key = piece.substring(0, piece.indexOf(':'));
            String value = piece.substring(piece.indexOf(':') + 1);

            return null;
        }

        private Object deserialize(String str) {
            char startChar = str.charAt(0);
            if(startChar=='N')
                return null;
            String serializeString = str.substring(1);
            switch (startChar) {
                case 'I':
                    return Integer.valueOf(serializeString);
                case 'J':
                    return Long.valueOf(serializeString);
                case 'S':
                    return Short.valueOf(serializeString);
                case 'Z':
                    return Boolean.valueOf(serializeString);
                case 'B':
                    return Byte.valueOf(serializeString);
                case 'C':
                    return Character.valueOf(serializeString.charAt(0));
                case 'D':
                    return Double.valueOf(serializeString);
                case 'F':
                    return Float.valueOf(serializeString);
                case 'T':
                    return serializeString.substring(1,serializeString.length()-1);
                case 'O':
                    String className = serializeString.substring(0,serializeString.indexOf('['));
                    String serializeElements = serializeString.substring(serializeString.indexOf('[')+1,serializeString.indexOf(']'));
                    try {
                        Class collectionClass = Class.forName(className);
                        Collection collection = (Collection) collectionClass.newInstance();

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    return null;
                case 'P':
                    return null;
                case 'L':
                    return null;
                default:
                    throw new RuntimeException("deserialize error: "+str);
            }
        }

        public boolean hasNextEntry() {
            int before = index;
            indexNextComma();
            if (index == lastIndex) {
                return false;
            }
            index = before;
            return true;
        }

        private void indexNextComma() {
            int rightBracketNum = 0;
            while (true) {
                char c = string.charAt(index);
                if (c == ',' && rightBracketNum == 0) {
                    return;
                } else if (c == '[')
                    rightBracketNum++;
                else if (c == ']')
                    rightBracketNum--;
                index++;
            }
        }
    }
}

package net.codevmc.extraItems.item.serialization;

import java.util.HashMap;
import java.util.Map;

public class SerializationHelper {

    private HashMap<String, Object> map = new HashMap<>();

    public static SerializationHelper helper() {
        return new SerializationHelper();
    }

    public SerializationHelper put(String key, Object o) {
        this.map.put(key, o);
        return this;
    }

    public Map<String, Object> build() {
        return map;
    }
}

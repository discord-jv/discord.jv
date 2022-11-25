package com.seailz.javadiscordwrapper.utils.json;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a JSON object
 *
 * @author Seailz
 * @since 1.0
 * @see    com.seailz.javadiscordwrapper.utils.json.JSONArray
 */
public interface JSON {

    Map<String, Object> items = new HashMap<>();

    default void add(String key, Object value) {
        items.put(key, value);
    }

    default void remove(String key) {
        items.remove(key);
    }

    default Object get(String key) {
        return items.get(key);
    }

    default String getString(String key) {
        return (String) items.get(key);
    }

    default int getInt(String key) {
        return (int) items.get(key);
    }

    default boolean getBoolean(String key) {
        return (boolean) items.get(key);
    }

    default double getDouble(String key) {
        return (double) items.get(key);
    }


    default Map<String, Object> getItems() {
        return items;
    }

    String toJson();

    JSON fromString(String string);



}

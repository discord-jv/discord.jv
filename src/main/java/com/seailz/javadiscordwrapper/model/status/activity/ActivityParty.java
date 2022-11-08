package com.seailz.javadiscordwrapper.model.status.activity;

import com.seailz.javadiscordwrapper.core.Compilerable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents the party of an activity
 * @param id The id of the party
 * @param currentSize The current size of the party
 * @param maxSize The max size of the party
 *
 * @author Seailz
 * @since  1.0
 * @see    Activity
 */
public record ActivityParty(
    String id,
    int currentSize,
    int maxSize
) implements Compilerable {

    @Override
    public JSONObject compile() {
        return new JSONObject()
                .put("id", id)
                .put("current_size", currentSize)
                .put("max_size", maxSize);
    }

    public static ActivityParty decompile(JSONObject obj) {
        String id;
        int currentSize;
        int maxSize;

        try {
            id = obj.getString("id");
        } catch (JSONException e) {
            id = null;
        }

        try {
            currentSize = obj.getJSONArray("size").getInt(0);
        } catch (JSONException e) {
            currentSize = 0;
        }

        try {
            maxSize = obj.getJSONArray("size").getInt(1);
        } catch (JSONException e) {
            maxSize = 0;
        }

        return new ActivityParty(id, currentSize, maxSize);
    }
}

package com.pigadoor.parsers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.pigadoor.data.Coordinates;

import java.lang.reflect.Type;

/**
 * Parser Coordinates class of SpaceMarine
 */
public class CoordinatesParser implements JsonDeserializer<Coordinates> {

    @Override
    public Coordinates deserialize(JsonElement jsonElement, Type type,
                                   JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Double x;
        try {
            x = jsonObject.get("x").getAsDouble();
        } catch (NumberFormatException e) {
            x = null;
        }
        Float y;
        try {
            y = jsonObject.get("y").getAsFloat();
        } catch (NumberFormatException e) {
            y = null;
        }
        return new Coordinates(x, y);
    }

}
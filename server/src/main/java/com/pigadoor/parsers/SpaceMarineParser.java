package com.pigadoor.parsers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser SpaceMarine class
 */
public class SpaceMarineParser implements JsonDeserializer<SpaceMarine> {


    @Override
    public SpaceMarine deserialize(JsonElement jsonElement, Type type,
                                   JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Integer id;
            try {
                id = jsonObject.get("id").getAsInt();
            } catch (NumberFormatException numberFormatException) {
                id = null;
            }

            String name;
            try {
                name = jsonObject.get("name").getAsString();
            } catch (NumberFormatException numberFormatException) {
                name = null;
            }

            Coordinates coordinates = jsonDeserializationContext
                    .deserialize(jsonObject.get("coordinates"), Coordinates.class);

            String stringTime;
            LocalDateTime localDateTime;
            try {
                stringTime = jsonObject.get("creationDate").getAsString();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");
                localDateTime = LocalDateTime.parse(stringTime, formatter);
            } catch (NumberFormatException numberFormatException) {
                localDateTime = null;
            }

            Integer health;
            try {
                health = jsonObject.get("health").getAsInt();
            } catch (NumberFormatException numberFormatException) {
                health = null;
            }

            Long heartCount;
            try {
                heartCount = jsonObject.get("heartCount").getAsLong();
            } catch (NumberFormatException numberFormatException) {
                heartCount = null;
            }

            Float height;
            try {
                height = jsonObject.get("height").getAsFloat();
            } catch (NumberFormatException numberFormatException) {
                height = null;
            }

            MeleeWeapon meleeWeapon;
            try {
                String stringWeapon = jsonObject.get("meleeWeapon").getAsString().toUpperCase();
                meleeWeapon = MeleeWeapon.valueOf(stringWeapon);
            } catch (NumberFormatException numberFormatException) {
                meleeWeapon = null;
            }

            Chapter chapter = jsonDeserializationContext
                    .deserialize(jsonObject.get("chapter"), Chapter.class);

            int finalId = 0;
            if (id != null) {
                finalId = id;
            }

            int finalHealth = 0;
            if (health != null) {
                finalHealth = health;
            }

            float finalHeight = 0;
            if (height != null) {
                finalHeight = height;
            }

            SpaceMarine spaceMarine = new SpaceMarine(finalId, name, coordinates, localDateTime, finalHealth, heartCount,
                    finalHeight, meleeWeapon, chapter);
            return spaceMarine;
        } catch (NullPointerException nullPointerException) {
            return null;
        }
    }

}

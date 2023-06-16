package com.pigadoor.parsers;

import com.google.gson.*;

import com.pigadoor.data.Chapter;

import java.lang.reflect.Type;

/**
 * Parser Chapter class of SpaceMarine
 */
public class ChapterParser implements JsonDeserializer<Chapter> {

    @Override
    public Chapter deserialize(JsonElement jsonElement, Type type,
                               JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String chapterName;
        try {
            chapterName = jsonObject.get("name").getAsString();
        } catch (NumberFormatException e) {
            chapterName = null;
        }
        String parentLegion;
        try {
            parentLegion = jsonObject.get("parentLegion").getAsString();
        } catch (NumberFormatException e) {
            parentLegion = null;
        }
        return new Chapter(chapterName, parentLegion);
    }

}
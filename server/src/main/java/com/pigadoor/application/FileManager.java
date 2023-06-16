package com.pigadoor.application;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.SpaceMarine;
import com.pigadoor.parsers.*;
import com.pigadoor.wrappers.SpaceMarineWrapper;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class FileManager {

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(SpaceMarine.class, new SpaceMarineParser())
            .registerTypeAdapter(Chapter.class, new ChapterParser())
            .registerTypeAdapter(Coordinates.class, new CoordinatesParser())
            .registerTypeAdapter(TreeMap.class, new SpaceMarinesParser())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeParser())
            .create();

    private static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        try {
            T[] arr = gson.fromJson(s, clazz);
            if (arr == null) {
                return new ArrayList<>();
            }
            return Arrays.asList(arr); //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
        } catch (JsonSyntaxException jsonSyntaxException) {
            return new ArrayList<T>();
        }
    }

    private static TreeMap<Integer, SpaceMarine> deserialize(String data) {
        List<SpaceMarine> linkedList = stringToArray(data, SpaceMarine[].class);
        //SpaceMarine[] array = gson.fromJson(data, LinkedList.class);
        TreeMap<Integer, SpaceMarine> collection = new TreeMap<>();
        if (linkedList == null) {
            return new TreeMap<>();
        } else {
            for (SpaceMarine current : linkedList) {
                if (current == null) continue;
                if (validateSpaceMarine(current)) {
                    collection.put(current.getId(), current);
                }
            }
            return collection;
        }
    }

    private static  boolean validateSpaceMarine(SpaceMarine spaceMarine) {
        if (spaceMarine.getId() <= 0) return false;
        if (spaceMarine.getName() == null) return false;
        if (spaceMarine.getName().isEmpty()) return false;
        if (spaceMarine.getCoordinates() == null) return false;
        if (spaceMarine.getCoordinates().getX() == null) return false;
        if (spaceMarine.getCoordinates().getY() == null) return false;
        if (spaceMarine.getCoordinates().getY() > 589) return false;
        if (spaceMarine.getCreationDate() == null) return false;
        if (spaceMarine.getHealth() <= 0) return false;
        if (spaceMarine.getHeartCount() == null) return false;
        if (spaceMarine.getHeartCount() <= 0) return false;
        if (spaceMarine.getHeartCount() > 3) return false;
        if (spaceMarine.getMeleeWeapon() == null) return false;
        if (spaceMarine.getChapter() != null) {
            if (spaceMarine.getChapter().getName() == null) {
                return false;
            }
            if (spaceMarine.getChapter().getName().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static String serialize(TreeMap<Integer, SpaceMarine> collection) {
        List<SpaceMarineWrapper> list = new ArrayList<>();
        for (Map.Entry<Integer, SpaceMarine> entry : collection.entrySet()) {
            SpaceMarine spaceMarine = entry.getValue();
            SpaceMarineWrapper spaceMarineWrapper = new SpaceMarineWrapper();
            spaceMarineWrapper.setId(spaceMarine.getId());
            spaceMarineWrapper.setName(spaceMarine.getName());
            spaceMarineWrapper.setCoordinates(spaceMarine.getCoordinates());
            spaceMarineWrapper.setCreationDate(spaceMarine.getCreationDate().toString());
            spaceMarineWrapper.setHealth(spaceMarine.getHealth());
            spaceMarineWrapper.setHeartCount(spaceMarine.getHeartCount());
            spaceMarineWrapper.setHeight(spaceMarine.getHeight());
            spaceMarineWrapper.setMeleeWeapon(spaceMarine.getMeleeWeapon());
            spaceMarineWrapper.setChapter(spaceMarine.getChapter());
            list.add(spaceMarineWrapper);
        }

        return gson.toJson(list);
    }

    public static void saveCollection(TreeMap<Integer, SpaceMarine> collection, String filepath) {
        String dataForSaving = serialize(collection);
        try (OutputStream os = new FileOutputStream(filepath);
             OutputStreamWriter osw = new OutputStreamWriter(os)) {
            osw.write(dataForSaving);
            osw.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static TreeMap<Integer, SpaceMarine> downloadCollection(String filepath) {
        StringJoiner fileData = new StringJoiner("\n");
        try (FileReader reader = new FileReader(filepath);
             Scanner scanner = new Scanner(reader)) {
            while (scanner.hasNextLine()) {
                fileData.add(scanner.nextLine());
            }
            List<SpaceMarine> linkedList = stringToArray(fileData.toString(), SpaceMarine[].class);
            TreeMap<Integer, SpaceMarine> collection = new TreeMap<>();
            if (linkedList.isEmpty()) {
                return new TreeMap<>();
            } else {
                for (SpaceMarine current : linkedList) {
                    if (current == null) continue;
                    if (validateSpaceMarine(current)) {
                        collection.put(current.getId(), current);
                    }
                }
                return collection;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
            return new TreeMap<>();
        } catch (NullPointerException e) {
            System.err.println("Environment variable not set");
            System.exit(1);
            return new TreeMap<>();
        }

    }

}

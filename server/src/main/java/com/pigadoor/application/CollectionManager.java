package com.pigadoor.application;

import com.pigadoor.data.SpaceMarine;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class CollectionManager {

    private TreeMap<Integer, SpaceMarine> collection = new TreeMap<>();
    private java.time.LocalDate creationDate;
    private String filepath;

    private static CollectionManager INSTANCE;

    private CollectionManager(String filepath) {
        this.collection = FileManager.downloadCollection(filepath);
        System.out.println("Collection downloaded. Items number: " + this.collection.size());
        this.creationDate = LocalDate.now();
        this.filepath = filepath;
    }

    public static CollectionManager getInstance(String filepath) {
        if (filepath == null || filepath.isEmpty()) {
            System.err.println("Cannot find environment variable 'LAB' with path to collection.");
            System.exit(1);
        }
        if (INSTANCE == null) {
            return new CollectionManager(filepath);
        } else {
            return INSTANCE;
        }
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getMaxId() {
        int maxID = 0;
        for (Map.Entry<Integer, SpaceMarine> treemapEntry : collection.entrySet()) {
            SpaceMarine spacemarine = treemapEntry.getValue();
            if (spacemarine.getId() > maxID) {
                maxID = spacemarine.getId();
            }
        }
        return maxID;
    }

    public TreeMap<Integer, SpaceMarine> getCollection() {
        return collection;
    }

    public String getFilepath() {
        return filepath;
    }

}

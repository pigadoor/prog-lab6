package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

public class CountGreaterThanHeightCommand {

    private final CollectionManager collectionManager;

    public CountGreaterThanHeightCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(float height) {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        long number = collection.values()
                .stream()
                .filter(e -> e.getHeight() < height)
                .count();
        return "Command executed. Found " + number + " items.\n";
    }

}

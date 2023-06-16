package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.List;
import java.util.TreeMap;

public class FilterGreaterThanHealthCommand {

    private CollectionManager collectionManager;

    public FilterGreaterThanHealthCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(int health) {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        List<SpaceMarine> result = collection.values()
                .stream()
                .filter(sm -> sm.getHealth() > health)
                .toList();
        return result.toString();
    }

}

package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

public class RemoveGreaterCommand {

    private CollectionManager collectionManager;

    public RemoveGreaterCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(SpaceMarine spaceMarine) {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        int initialSize = collection.size();
        collection.entrySet().removeIf(entry -> entry.getValue().getId() > spaceMarine.getId());
        return "Command executed. Removed: " + (initialSize - collection.size()) + " items.\n";
    }
}

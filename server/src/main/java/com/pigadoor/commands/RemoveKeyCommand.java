package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

public class RemoveKeyCommand {

    private final CollectionManager collectionManager;

    public RemoveKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Integer key) {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        boolean isRemoved = collection.entrySet().removeIf(entry -> entry.getKey().equals(key));
        return isRemoved ? "Item has been removed from collection.\n" :
                "Item has not been removed from collection because this ID is not exists.\n";
    }
}

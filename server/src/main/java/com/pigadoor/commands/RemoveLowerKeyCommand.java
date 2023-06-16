package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

public class RemoveLowerKeyCommand {

    private final CollectionManager collectionManager;

    public RemoveLowerKeyCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Integer key) {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        int initialSize = collection.size();
        collection.tailMap(key, false).clear();
        return "Command executed. Removed " + (initialSize - collection.size()) + " items.\n";
    }

}

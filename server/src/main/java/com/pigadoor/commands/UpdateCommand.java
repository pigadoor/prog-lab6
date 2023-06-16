package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;
import com.sun.source.tree.Tree;

import java.util.TreeMap;

public class UpdateCommand {

    private final CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Integer key, SpaceMarine newSpaceMarine) {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        return collection.containsKey(key) ? collection.compute(key, (k, v) -> newSpaceMarine) != null ?
                "Item has been updated.\n" : "Item hasn't been updated.\n" :
                "Item hasn't been updated because given key is not contains in the collection.\n";
    }

}

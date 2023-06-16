package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;
import com.sun.source.tree.Tree;

import java.util.TreeMap;


public class ShowCommand {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        StringBuilder result = new StringBuilder();
        collection.forEach((key, value) -> result.append(key).append(" : ").append(value).append("\n"));
        return result.toString();
    }

}

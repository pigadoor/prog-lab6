package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.TreeMap;

public class InfoCommand {

    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        return new StringBuilder()
                .append("Type of collection: ")
                .append(collectionManager.getCollection().getClass().getName()).append("\n")
                .append("Number of items: ").append(collectionManager.getCollection().size()).append("\n")
                .append("Initialization date: ").append(collectionManager.getCreationDate()).toString();
    }

}

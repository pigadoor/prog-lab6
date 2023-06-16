package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

public class InsertCommand {

    private final CollectionManager collectionManager;

    public InsertCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute(Integer key, SpaceMarine spaceMarine) {
        this.collectionManager.getCollection().put(key, spaceMarine);
        return "Item has been added.\n";
    }


}

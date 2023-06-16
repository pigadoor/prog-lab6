package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;

public class ClearCommand {

    private final CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        this.collectionManager.getCollection().clear();
        return "All collection items has been removed.\n";
    }

}

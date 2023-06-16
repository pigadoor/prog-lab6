package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.application.FileManager;

public class SaveCommand {

    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        System.out.println(this.collectionManager.getCollection());
        System.out.println(this.collectionManager.getFilepath());
        FileManager.saveCollection(this.collectionManager.getCollection(), this.collectionManager.getFilepath());
        return "Collection saved.\n";
    }

}

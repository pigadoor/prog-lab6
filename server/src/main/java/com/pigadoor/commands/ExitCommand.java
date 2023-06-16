package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;

public class ExitCommand {

    private final CollectionManager collectionManager;

    public ExitCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
         System.exit(0);
         return "Program finished.\n";
    }

}

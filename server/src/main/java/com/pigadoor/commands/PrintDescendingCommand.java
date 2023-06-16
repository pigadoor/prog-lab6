package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.SpaceMarine;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PrintDescendingCommand {

    private CollectionManager collectionManager;

    public PrintDescendingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        TreeMap<Integer, SpaceMarine> collection = this.collectionManager.getCollection();
        List<SpaceMarine> result = collection.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .map(Map.Entry::getValue)
                .toList();
        return result.toString();
    }

}

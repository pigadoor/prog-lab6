package com.pigadoor.application;

import com.pigadoor.commands.*;
import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;
import com.pigadoor.network.Request;
import com.pigadoor.network.Response;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandInvoker {

    private final CollectionManager collectionManager;

    public CommandInvoker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }


    public void listenForRequests() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[65536];
        // Создаем DatagramSocket, чтобы соединить его с портом UDP на локальной машине и слушать запросы
        DatagramSocket socket = new DatagramSocket(4242);
        LinkedList<String> history = new LinkedList<>();
        while (true) {
            // Создаем пакет для получения запроса
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String request1 = new String(packet.getData());
            Request request = new Request();
            String[] parts = request1.split("=");
            request.setCommand(parts[0]);
            if (!parts[1].equals("null")) request.setSpaceMarine(deserializeFromCSV(parts[1]));
            if (!parts[2].equals("null")) request.setKey(Integer.parseInt(parts[2]));
            if (!parts[3].equals("null")) request.setHealth(Integer.parseInt(parts[3]));
            if (!parts[4].equals("null")) request.setHeight(Float.parseFloat(parts[4]));
            if (history.size() == 10) {
                history.removeLast();
            }
            history.add(request.getCommand());
            // Анализируем полученный запрос и формируем сообщение, которое отправляем обратно
            String response = "";
            switch (request.getCommand()) {
                case "clear":
                   response = new ClearCommand(this.collectionManager).execute();
                    break;
                case "count_greater_than_height":
                    if (request.getHeight() == null) {
                        response = "Incorrect argument. Height should be defined.\n";
                    } else {
                        response = new CountGreaterThanHeightCommand(this.collectionManager).execute(request.getHeight());
                    }
                    break;
                case "exit":
                    response = "Command is unavailable.\n";
                    break;
                case "help":
                    response = new HelpCommand(this.collectionManager).execute();
                    break;
                case "filter_greater_than_health":
                    if (request.getHealth() == null) {
                        response = "Incorrect argument. Health should be defined.\n";
                    } else {
                        response = new FilterGreaterThanHealthCommand(this.collectionManager)
                                .execute(request.getHealth());
                    }
                    break;
                case "info":
                    response = new InfoCommand(this.collectionManager).execute();
                    break;
                case "insert":
                    if (request.getKey() == null || request.getSpaceMarine() == null) {
                        response = "Incorrect arguments. Key and space marine for insertion should be defined.\n";
                    } else {
                        SpaceMarine spaceMarine1 = request.getSpaceMarine();
                        spaceMarine1.setId(request.getKey());
                        response = new InsertCommand(this.collectionManager).execute(request.getKey(),
                               spaceMarine1);
                    }
                    break;
                case "print_descending":
                    response = new PrintDescendingCommand(this.collectionManager).execute();
                    break;
                case "remove_greater":
                    if (request.getSpaceMarine() == null) {
                        response = "Incorrect argument. Space marine should be defined.\n";
                    } else {
                        response = new RemoveGreaterCommand(this.collectionManager).execute(request.getSpaceMarine());
                    }
                    break;
                case "remove_key":
                    if (request.getKey() == null) {
                        response = "Incorrect argument. Key should be defined.\n";
                    } else {
                        response = new RemoveKeyCommand(this.collectionManager).execute(request.getKey());
                    }
                    break;
                case "remove_lower_key":
                    if (request.getKey() == null) {
                        response = "Incorrect argument. Key should be defined.\n";
                    } else {
                        response = new RemoveLowerKeyCommand(this.collectionManager).execute(request.getKey());
                    }
                    break;
                case "save":
                    response = "Auto-save is enabled. Don't worry!\n";
                    break;
                case "show":
                    response = new ShowCommand(this.collectionManager).execute();
                    break;
                case "history":
                    response = history.toString();
                    break;
                case "update":
                    if (request.getKey() == null || request.getSpaceMarine() == null) {
                        response = "Incorrect arguments. Key and space marine for updating should be defined.\n";
                    } else {
                        SpaceMarine spaceMarine2 = request.getSpaceMarine();
                        spaceMarine2.setId(request.getKey());
                        response = new UpdateCommand(this.collectionManager).execute(request.getKey(),
                                spaceMarine2);
                        break;
                    }
                default:
                    response = "Unknown command. Write help for getting list of available commands.\n";
                    break;
            }
            new SaveCommand(collectionManager).execute();
            // Отправляем ответ обратно отправителю
            byte[] responseData = response.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                    packet.getAddress(), packet.getPort());
            socket.send(responsePacket);
        }
    }

    public String serializeToCSV(SpaceMarine spaceMarine) {
        List<String> fields = Arrays.asList(
                String.valueOf(spaceMarine.getId()),
                spaceMarine.getName(),
                String.valueOf(spaceMarine.getCoordinates().getX()),
                String.valueOf(spaceMarine.getCoordinates().getY()),
                spaceMarine.getCreationDate().toString(),
                String.valueOf(spaceMarine.getHealth()),
                String.valueOf(spaceMarine.getHeartCount()),
                String.valueOf(spaceMarine.getHeight()),
                spaceMarine.getMeleeWeapon().toString(),
                spaceMarine.getChapter() != null ? spaceMarine.getChapter().getName() : ""
        );
        return fields.stream()
                .map(field -> field.replace(",", "\\,"))
                .collect(Collectors.joining(","));
    }

    // Метод для преобразования CSV-строки в объект SpaceMarine
    public static SpaceMarine deserializeFromCSV(String csv) {
        System.out.println(csv);
        String[] fields = csv.split(",", -1);
        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        double x = Double.parseDouble(fields[2]);
        float y = Float.parseFloat(fields[3]);
        Coordinates coordinates = new Coordinates(x, y);
        java.time.LocalDateTime creationDate = java.time.LocalDateTime.parse(fields[4]);
        int health = Integer.parseInt(fields[5]);
        long heartCount = Long.parseLong(fields[6]);
        float height = Float.parseFloat(fields[7]);
        MeleeWeapon meleeWeapon = MeleeWeapon.valueOf(fields[8]);
        Chapter chapter = null;
        if (!fields[9].isEmpty()) {
            String chapterName = fields[9].replace("\\,", ",");
            chapter = new Chapter(chapterName, null);
        }

        SpaceMarine spaceMarine = new SpaceMarine();
        spaceMarine.setId(id);
        spaceMarine.setName(name);
        spaceMarine.setCoordinates(coordinates);
        spaceMarine.setCreationDate(creationDate);
        spaceMarine.setHealth(health);
        spaceMarine.setHeartCount(heartCount);
        spaceMarine.setHeight(height);
        spaceMarine.setMeleeWeapon(meleeWeapon);
        spaceMarine.setChapter(chapter);

        return spaceMarine;
    }
}

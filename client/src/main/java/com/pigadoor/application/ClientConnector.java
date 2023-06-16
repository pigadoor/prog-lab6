package com.pigadoor.application;

import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;
import com.pigadoor.network.Request;
import com.pigadoor.network.Response;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientConnector {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buffer;

    public void run() {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);
        InputManager inputManager = new InputManager();
        try {
            socket = new DatagramSocket();
            while (true) {
                // Wait for user input
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                String[] args;
                String[] splitCommand = command.split(" ", 3);
                Request request = new Request();
                switch (splitCommand[0]) {
                    case "clear":
                        request.setCommand("clear");
                        break;
                    case "count_greater_than_height":
                        try {
                            request.setCommand("count_greater_than_height");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Float height = Float.parseFloat(splitCommand[1]);
                            request.setHeight(height);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            continue;
                        }
                        break;
                    case "exit":
                        System.out.println("Finishing a program!\n");
                        System.exit(0);
                        break;
                    case "help":
                        request.setCommand("help");
                        break;
                    case "filter_greater_than_health":
                        try {
                            request.setCommand("filter_greater_than_health");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer health = Integer.parseInt(splitCommand[1]);
                            request.setHealth(health);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            continue;
                        }
                        break;
                    case "info":
                        request.setCommand("info");
                        break;
                    case "insert":
                        try {
                            request.setCommand("insert");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer key = Integer.parseInt(splitCommand[1]);
                            request.setKey(key);
                            SpaceMarine spaceMarine1 = inputManager.receiveSpaceMarine();
                            request.setSpaceMarine(spaceMarine1);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            continue;
                        }
                        break;
                    case "print_descending":
                        request.setCommand("print_descending");
                        break;
                    case "remove_greater":
                        SpaceMarine spaceMarine2 = inputManager.receiveSpaceMarine();
                        request.setSpaceMarine(spaceMarine2);
                        break;
                    case "remove_key":
                        try {
                            request.setCommand("remove_key");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer key = Integer.parseInt(splitCommand[1]);
                            request.setKey(key);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            continue;
                        }
                        break;
                    case "remove_lower_key":
                        try {
                            request.setCommand("remove_lower_key");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer key = Integer.parseInt(splitCommand[1]);
                            request.setKey(key);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            continue;
                        }
                        break;
                    case "save":
                        System.out.println("Auto-save is enabled. Don't worry!\n");
                        break;
                    case "show":
                        request.setCommand("show");
                        break;
                    case "history":
                        request.setCommand("history");
                        break;
                    case "update":
                        try {
                            request.setCommand("update");
                            if (splitCommand.length == 1) throw new NumberFormatException();
                            Integer key = Integer.parseInt(splitCommand[1]);
                            request.setKey(key);
                            SpaceMarine spaceMarine1 = inputManager.receiveSpaceMarine();
                            request.setSpaceMarine(spaceMarine1);
                        } catch (NumberFormatException numberFormatException) {
                            System.out.println("Incorrect arguments. Write help for getting list of commands" +
                                    " and descriptions. Not sent.");
                            continue;
                        }
                        break;
                    default:
                        request.setCommand("unknown");
                        break;
                }
                String send = request.getCommand() + "=" + (request.getSpaceMarine() != null ? serializeToCSV(request.getSpaceMarine()) : "null") + "=" + request.getKey() + "=" + request.getHealth() + "=" + request.getHeight() + "=" + request.getScript();
                // Send request to server
                byte[] requestData = send.getBytes();
                DatagramPacket packet = new DatagramPacket(requestData, requestData.length, InetAddress.getByName("localhost"), 4242);
                socket.send(packet);

                // Receive response from server
                byte[] responseData = new byte[65536];
                packet = new DatagramPacket(responseData, responseData.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                // Deserialize response object

                // Print response message
                System.out.println("Response:\n " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
            scanner.close();
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

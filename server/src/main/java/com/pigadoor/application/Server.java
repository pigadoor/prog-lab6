package com.pigadoor.application;

import java.io.IOException;

public class Server {

    public static void main(String[] args) {
        try {
            String filepath = System.getenv("LAB");
            CollectionManager collectionManager = CollectionManager.getInstance(filepath);
            CommandInvoker commandInvoker = new CommandInvoker(collectionManager);
            commandInvoker.listenForRequests();
        } catch (IOException e) {
            System.out.println("File reading error.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
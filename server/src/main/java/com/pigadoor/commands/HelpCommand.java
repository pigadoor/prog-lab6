package com.pigadoor.commands;

import com.pigadoor.application.CollectionManager;

public class HelpCommand {

    private final CollectionManager collectionManager;

    public HelpCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String execute() {
        return "help : вывести справку по доступным командам\n"
                + "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, " +
                "количество элементов и т.д.)\n"
                + "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n"
                + "insert null {element} : добавить новый элемент с заданным ключом\n"
                + "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n"
                + "remove_key null : удалить элемент из коллекции по его ключу\n"
                + "clear : очистить коллекцию\n"
                + "save : сохранить коллекцию в файл\n"
//                + "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся " +
//                "команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n"
                + "exit : завершить программу\n"
                + "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n"
                + "history : вывести последние 8 команд (без их аргументов)\n"
                + "remove_lower_key null : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n"
                + "count_less_than_height height : вывести количество элементов, значение поля height которых меньше " +
                "заданного\n"
                + "filter_greater_than_health health : вывести элементы, значение поля health которых больше заданного\n"
                + "print_descending : вывести элементы коллекции в порядке убывания";
    }

}

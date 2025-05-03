package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

import java.util.Scanner;

public class AddIfMax implements Command {
    @Override
    public String getDescription() {
        return "add_if_max {element} - добавление" +
                "нового элемента в коллекцию, если его значение превышает" +
                "значение наибольшего элемента этой коллекции";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            CollectionManager collectionManager = core.getCollectionManager();
            SpaceMarine marine = core.getBuilder().buildSpacemarine(scanner);
            if (marine.compareTo(collectionManager.getCollection().getLast()) > 0) {
                collectionManager.addElement(marine);
                System.out.println("Элемент добавлен");
            } else {
                System.out.println("Элемент не соответствует условиям");
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

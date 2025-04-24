package org.example.commands;

import org.example.Exceptions.RedundantArguments;
import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

public class AddIfMax implements Command {
    @Override
    public String getDescription() {
        return "add_if_max {element} - добавление" +
                "нового элемента в коллекцию, если его значение превышает" +
                "значение наибольшего элемента этой коллекции";
    }

    @Override
    public void execute(Core core, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArguments();
            }
            CollectionManager collectionManager = core.getCollectionManager();
            SpaceMarine marine = core.getBuilder().buildSpacemarine();
            if (marine.compareTo(collectionManager.getCollection().getFirst()) > 0) {
                collectionManager.addElement(marine);
                System.out.println("Элемент добавлен");
            } else {
                System.out.println("Элемент не соответствует условиям");
            }
        } catch (RedundantArguments e) {
            System.out.println(e.printProblem(args));
        }
    }
}

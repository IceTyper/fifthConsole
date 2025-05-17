package org.example.commands;

import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.util.Scanner;

public class Info implements Command {
    @Override
    public String getDescription() {
        return "info - вывод информации о коллекции";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            CollectionManager collectionManager = core.getCollectionManager();
            core.getIOManager().printMessage("Дата инициализации коллекции: " + collectionManager.getCreationDate() +
                    "\nТип коллекции: " + collectionManager.getCollectionType().getSimpleName() +
                    "\nКоличество элементов в коллекции: " + collectionManager.getCollection().size());
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

package org.example.commands;

import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

import java.util.Deque;
import java.util.Scanner;

public class RemoveLower implements Command {
    @Override
    public String getDescription() {
        return "remove_lower {element} - удаление из коллекции всех элементов, " +
                "меньше заданного";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 2) {
                throw new RedundantArgumentsException();
            }
            Deque<SpaceMarine> collection = core.getCollectionManager().getCollection();
            SpaceMarine comparingMarine = core.getBuilder().buildSpacemarine(scanner);
            int size = collection.size();
            collection.removeIf(spaceMarine -> comparingMarine.compareTo(spaceMarine) > 0);
            if (collection.size() < size) {
                System.out.println("Элементы, меньшие, чем заданный, изничтожены");
            } else {
                System.out.println("Меньшие элементы не найдены");
            }

        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }

    }
}

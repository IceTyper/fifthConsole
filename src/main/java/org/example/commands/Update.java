package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

import java.util.Deque;
import java.util.Scanner;

public class Update implements Command {
    @Override
    public String getDescription() {
        return "update {id} {element} - обновление значения элемента коллекции, " +
                "id которого равен заданному";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 2) {
                throw new RedundantArgumentsException();
            }
            if (args.length == 1) {
                System.out.println("Вы не ввели айдишник");
            } else {
                try {
                    int id = Integer.parseInt(args[1]);
                    Deque<SpaceMarine> collection = core.getCollectionManager().getCollection();
                    boolean isFound = false;
                    for (SpaceMarine spaceMarine : collection) {
                        if (spaceMarine.getId() == id) {
                            isFound = true;
                            core.getBuilder().updateSpacemarine(spaceMarine, scanner);
                            core.getIOManager().printMessage("Космический корабль успешно обновлён!");
                        }
                    }
                    if (!isFound) {
                        System.out.println("Корабля с данным ID не существует");
                    }
                } catch (NumberFormatException e) {
                    core.getIOManager().printError("Айдишник криво введён\n");
                }
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

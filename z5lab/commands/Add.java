package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

import java.util.Scanner;

public class Add implements Command {
    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            SpaceMarine spaceMarine = core.getBuilder().buildSpacemarine(scanner);
            core.getCollectionManager().addElement(spaceMarine);
            core.getIOManager().printMessage("Космический корабль успешно добавлен в коллекцию!");
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Произошли технические ошибки при создании корабля, его теперь нет :(");
        }
    }
}

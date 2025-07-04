package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

import java.util.Scanner;

public class SumOfHealth implements Command {
    @Override
    public String getDescription() {
        return "sum_of_health - вывод суммы значений поля health " +
                "для всех элементов коллекции";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            CollectionManager cManager = core.getCollectionManager();
            if (cManager.getCollection().isEmpty()) {
                System.out.println("Элементов нема");
            } else {
                Long sumOfHealth = 0L;
                for (SpaceMarine spaceMarine : cManager.getCollection()) {
                    sumOfHealth += spaceMarine.getHealth();
                }
                System.out.println("Сумма жизней всех космических кораблей: " + sumOfHealth);
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }

    }
}

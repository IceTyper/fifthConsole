package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class PrintFieldAscendingHealth implements Command {
    @Override
    public String getDescription() {
        return "print_field_ascending_health - вывод значений " +
                "поля health всех элементов в порядке возрастания";
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
                Deque<SpaceMarine> collection = new ArrayDeque<>(cManager.getCollection());
                Long[] healths = new Long[collection.size()];
                for (int i = 0; i < cManager.getCollection().size(); i++) {
                    healths[i] = collection.removeFirst().getHealth();
                }
                Arrays.sort(healths);
                System.out.println("Отсортированный список жизней: ");
                for (Long health : healths) {
                    System.out.println(health);
                }
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }

    }
}

package org.example.commands;

import org.example.important.Core;
import org.example.interfaces.Command;

import java.util.Deque;
import java.util.Scanner;

public class Show implements Command {
    @Override
    public String getDescription() {
        return "show - Вывод элементов коллекции в строковом представлении";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            Deque<?> collection = core.getCollectionManager().getCollection();
            if (!(collection == null || collection.isEmpty())) {
                for (Object element : collection) {
                    System.out.println("*******");
                    System.out.println(element);
                    System.out.println("*******");
                }
            } else {
                System.out.println("Элементов нет, сорь");
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

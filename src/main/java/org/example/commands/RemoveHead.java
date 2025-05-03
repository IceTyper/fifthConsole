package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.util.Deque;
import java.util.Scanner;

public class RemoveHead implements Command {
    @Override
    public String getDescription() {
        return "remove_head - вывод первого элемента коллекции и его удаление";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            Deque<?> collection = core.getCollectionManager().getCollection();
            if (collection.isEmpty()) {
                System.out.println("Элементов нет, не могу вывести первый элемент");
            } else {
                System.out.println(collection.removeFirst());
                core.getCollectionManager().sortCollection();
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

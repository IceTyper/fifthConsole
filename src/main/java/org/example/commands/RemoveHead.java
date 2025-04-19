package org.example.commands;

import org.example.Exceptions.RedundantArguments;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.util.Deque;

public class RemoveHead implements Command {
    @Override
    public String getDescription() {
        return "remove_head - вывод первого элемента коллекции и его удаление";
    }

    @Override
    public void execute(Core core, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArguments();
            }
            Deque<?> collection = core.getCollectionManager().getCollection();
            if (collection.isEmpty()) {
                System.out.println("Элементов нет, не могу вывести первый элемент");
            } else {
                System.out.println(collection.removeFirst());
                core.getCollectionManager().sortCollection();
            }
        } catch (RedundantArguments e) {
            System.out.println(e.printProblem(args));
        }
    }
}

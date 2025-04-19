package org.example.commands;

import org.example.Exceptions.RedundantArguments;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.util.Deque;

public class Clear implements Command {
    @Override
    public String getDescription() {
        return "clear - очищение коллекции";
    }

    @Override
    public void execute(Core core, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArguments();
            }
            Deque<?> collection = core.getCollectionManager().getCollection();
            if (!collection.isEmpty()) {
                collection.clear();
                System.out.println("Чистка проведена успешно");
            } else {
                System.out.println("Элементы не найдены");
            }
        } catch (RedundantArguments e) {
            System.out.println(e.printProblem(args));
        }
    }
}

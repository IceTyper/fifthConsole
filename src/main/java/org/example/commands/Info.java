package org.example.commands;

import org.example.Exceptions.RedundantArguments;
import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;

public class Info implements Command {
    @Override
    public String getDescription() {
        return "info - вывод информации о коллекции";
    }

    @Override
    public void execute(Core core, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArguments();
            }
            CollectionManager collectionManager = core.getCollectionManager();
            core.getIOManager().printMessage("Дата инициализации коллекции: " + collectionManager.getCreationDate() +
                    "\nТип коллекции: " + collectionManager.getCollectionType().getSimpleName() +
                    "\nКоличество элементов в коллекции: " + collectionManager.getCollection().size());
        } catch (RedundantArguments e) {
            System.out.println(e.printProblem(args));
        }
    }
}

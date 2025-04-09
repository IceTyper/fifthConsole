package commands;

import important.CollectionManager;
import important.Core;
import interfaces.Command;

public class Info implements Command {
    @Override
    public String getDescription() {
        return "info - вывод информации о коллекции";
    }

    @Override
    public void execute(Core core, String[] args) {
        CollectionManager<?> collectionManager = core.getCollectionManager();
        core.getIOManager().printMessage("Дата инициализации коллекции: " + collectionManager.getCreationDate() +
                "\nТип коллекции: " + collectionManager.getCollectionType().getSimpleName() +
                "\nКоличество элементов в коллекции: " + collectionManager.getCollection().size());
    }
}

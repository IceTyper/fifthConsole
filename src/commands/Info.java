package commands;

import important.CollectionManager;
import important.Core;
import models.SpaceMarine;

import java.util.Deque;

public class Info implements Command {
    @Override
    public String getDescription() {
        return "info - вывод информации о коллекции";
    }

    @Override
    public void execute(Core core) {
        CollectionManager<?> collectionManager = core.getCollectionManager();
        core.getIOManager().printMessage("Дата инициализации коллекции: " + collectionManager.getCreationDate() +
                "\nТип хранимых элементов: SpaceMarine\n" +
                "Количество элементов в коллекции: " + collectionManager.getCollection().size());
    }
}

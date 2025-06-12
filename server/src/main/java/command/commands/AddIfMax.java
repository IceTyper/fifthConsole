package command.commands;

import command.AbstractCommand;
import models.SpaceMarine;
import collection.CollectionHandable;
import collection.CollectionHandler;
import io.Builder;

import java.util.Deque;

public class AddIfMax extends AbstractCommand {
    private static CollectionHandable handler = new CollectionHandler();

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public Object[] execute(Object[] args) {
        Builder builder = new Builder();
        Deque<SpaceMarine> collection = handler.getCollection();
        SpaceMarine marine = builder.buildSpaceMarine(args);
        boolean shouldAdd = collection.isEmpty() || collection.stream().allMatch(a -> marine.compareTo(a) > 0);

        if (shouldAdd) {
            collection.addFirst(marine);
            long id = handler.getMaxId();
            return new Object[]{"Элемент успешно добавлен в коллекцию! ID элемента: " + id};
        } else {
            return new Object[]{"Элемент не добавлен, так как не превышает максимальный элемент коллекции"};
        }
    }
}

package commands;

import collection.*;
import collection.utility.CollectionControllable;
import collection.utility.CollectionHandler;
import io.Builder;

import java.util.ArrayDeque;
import java.util.Deque;

public class AddIfMax extends Command {
    private static CollectionControllable handler = new CollectionHandler();

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public Object[] execute() {
        Builder builder = new Builder();
        Deque<SpaceMarine> collection = handler.getCollection();
        SpaceMarine marine = builder.buildSpaceMarine(queue);
        queue.clear();
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

package command.commands;

import command.AbstractCommand;
import models.SpaceMarine;
import collection.CollectionControllable;
import collection.CollectionHandler;
import io.Builder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class RemoveLower extends AbstractCommand {
    private static CollectionControllable handler = new CollectionHandler();

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public Object[] execute(Object[] args) {
        Builder builder = new Builder();
        Deque<SpaceMarine> collection = handler.getCollection();
        SpaceMarine marine = builder.buildSpaceMarine(queue);
        queue.clear();
        int size = collection.size();

        handler.setCollection(collection.stream().
                filter(m -> m.compareTo(marine) > 0).
                collect(Collectors.toCollection(ArrayDeque::new)));

        int removed = collection.size();
        if (removed > 0) {
            return new Object[]{"Удалено элементов: " + removed};
        } else {
            return new Object[]{"Нет элементов, меньших заданного"};
        }
    }
}

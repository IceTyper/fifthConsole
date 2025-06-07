package commands;

import collection.*;
import collection.utility.CollectionControllable;
import collection.utility.CollectionHandler;
import io.Builder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Collectors;

public class RemoveLower extends Command {
    private static CollectionControllable handler = new CollectionHandler();

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public Object[] execute() {
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

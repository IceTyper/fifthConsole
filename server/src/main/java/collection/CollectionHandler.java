package collection;

import models.SpaceMarine;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class CollectionHandler implements CollectionControllable {
    private static final LocalDateTime creationDate;
    private static Deque<SpaceMarine> collection = new ArrayDeque<>();

    static {
        creationDate = LocalDateTime.now();
    }

    @Override
    public Deque<SpaceMarine> getCollection() {
        return collection;
    }

    @Override
    public void setCollection(Deque<SpaceMarine> collection) {
        CollectionHandler.collection = collection;
    }

    @Override
    public void sortCollection() {
        collection = collection.stream().sorted().collect(Collectors.toCollection(ArrayDeque::new));
    }

    @Override
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public long getMaxId() {
        return collection.stream()
                .mapToLong(SpaceMarine::getId)
                .max()
                .orElse(0);
    }
}

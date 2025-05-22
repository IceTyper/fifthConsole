package collection.models;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class CollectionHandler implements CollectionControllable {
    private static Deque<SpaceMarine> collection = new ArrayDeque<>();

    @Override
    public Deque<SpaceMarine> getCollection() {
        return collection;
    }


    @Override
    public void sortCollection() {
        collection = collection.stream().sorted().collect(Collectors.toCollection(ArrayDeque::new));
    }

    @Override
    public LocalDateTime getCreationDate() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void addFirst(SpaceMarine spaceMarine) {

    }

    @Override
    public void addLast(SpaceMarine spaceMarine) {

    }

    @Override
    public void removeFirst() {

    }
}

package collection.models;

import java.time.LocalDateTime;
import java.util.Deque;

public interface CollectionControllable {

    Deque<SpaceMarine> getCollection();

    void sortCollection();

    LocalDateTime getCreationDate();

    int size();

    void addFirst(SpaceMarine spaceMarine);

    void addLast(SpaceMarine spaceMarine);

    void removeFirst();
}

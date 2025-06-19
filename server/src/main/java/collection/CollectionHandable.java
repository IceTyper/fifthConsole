package collection;

import models.SpaceMarine;

import java.time.LocalDateTime;
import java.util.Deque;

public interface CollectionHandable {

    Deque<SpaceMarine> getCollection();

    void setCollection(Deque<SpaceMarine> collection);

    long add(SpaceMarine spaceMarine);

    void sortCollection();

    LocalDateTime getCreationDate();

    int size();

    long clear(String username);

    boolean remove(SpaceMarine spaceMarine);

    boolean updateElement(SpaceMarine spaceMarine);
}

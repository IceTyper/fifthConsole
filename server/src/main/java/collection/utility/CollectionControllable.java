package collection.utility;

import collection.SpaceMarine;

import java.time.LocalDateTime;
import java.util.Deque;

public interface CollectionControllable {

    Deque<SpaceMarine> getCollection();

    void setCollection(Deque<SpaceMarine> collection);

    void sortCollection();

    LocalDateTime getCreationDate();

    int size();

    long getMaxId();
}

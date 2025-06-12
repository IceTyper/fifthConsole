package collection;

import models.SpaceMarine;

import java.time.LocalDateTime;
import java.util.Deque;

public interface CollectionHandable {

    Deque<SpaceMarine> getCollection();

    void setCollection(Deque<SpaceMarine> collection);

    void sortCollection();

    LocalDateTime getCreationDate();

    int size();

    long getMaxId();
}

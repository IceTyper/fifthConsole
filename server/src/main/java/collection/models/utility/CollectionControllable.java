package collection.models.utility;

import collection.models.SpaceMarine;

import java.time.LocalDateTime;
import java.util.Deque;

public interface CollectionControllable {

    Deque<SpaceMarine> getCollection();

    void sortCollection();

    LocalDateTime getCreationDate();

    int size();
}

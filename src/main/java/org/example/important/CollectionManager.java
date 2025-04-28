package org.example.important;

import org.example.models.SpaceMarine;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class CollectionManager {
    private final LocalDate creationDate;
    private Deque<SpaceMarine> collection;

    {
        creationDate = LocalDate.now();
    }

    public CollectionManager(ArrayDeque<SpaceMarine> collection) {
        this.collection = collection;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Deque<SpaceMarine> getCollection() {
        return collection;
    }

    public void setCollection(Deque<SpaceMarine> collection) {
        this.collection = collection;
        sortCollection();
    }

    public void addElement(SpaceMarine element) {
        collection.addFirst(element);
        sortCollection();
    }

    public Class<?> getCollectionType() {
        return collection.getClass();
    }

    public void sortCollection() {
        ArrayList<SpaceMarine> list = new ArrayList<>(collection);
        Collections.sort(list);
        collection = new ArrayDeque<>(list);
    }
}

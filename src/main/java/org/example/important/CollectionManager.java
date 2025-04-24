package org.example.important;

import org.example.models.SpaceMarine;

import java.time.LocalDate;
import java.util.*;

public class CollectionManager {
    private Deque<SpaceMarine> collection = new ArrayDeque<>();
    private final LocalDate creationDate;

    {
        creationDate = LocalDate.now();
    }

    public CollectionManager(ArrayDeque<SpaceMarine> collecSpaceMarineion) {this.collection = collection;}

    public LocalDate getCreationDate() {return creationDate;}

    public Deque<SpaceMarine> getCollection() {return collection;}

    public void setCollection(Deque<SpaceMarine> collection) {this.collection = collection; sortCollection();}

    public void addElement(SpaceMarine element) {collection.addFirst(element); sortCollection();}

    public Class<?> getCollectionType() { return collection.getClass();}

    public void sortCollection() {
        ArrayList<SpaceMarine> list = new ArrayList<>( );
        Collections.sort(list);
        collection = new ArrayDeque<>(list);
    }
}

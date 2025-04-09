package important;

import models.SpaceMarine;

import java.time.LocalDate;
import java.util.*;

public class CollectionManager<T> {
    private Deque<T> collection = new ArrayDeque<>();
    private LocalDate creationDate;

    {
        creationDate = LocalDate.now();
    }

    public CollectionManager(ArrayDeque<T> collection) {this.collection = collection;}

    public LocalDate getCreationDate() {return creationDate;}

    public Deque<T> getCollection() {return collection;}

    public void setCollection(Deque<T> collection) {this.collection = collection; sortCollection();}

    public void addElement(T element) {collection.addFirst(element); sortCollection();}

    public Class<?> getCollectionType() { return collection.getClass();}

    public void sortCollection() {
        List<SpaceMarine> list = new ArrayList<>((Collection<? extends SpaceMarine>) collection);
        Collections.sort(list);
    }
}

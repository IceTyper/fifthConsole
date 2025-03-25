package important;

import java.util.ArrayDeque;
import java.util.Deque;

public class CollectionManager<T> {
    private Deque<T> collection = new ArrayDeque<>();

    public CollectionManager(ArrayDeque<T> collection) {this.collection = collection;}

    public Deque<T> getCollection() {return collection;}

    public void setCollection(Deque<T> collection) {this.collection = collection;}

    public void addElement(T element) {collection.addFirst(element);}
}

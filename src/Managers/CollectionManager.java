package Managers;

import models.SpaceMarine;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class CollectionManager<T> {
    private Deque<T> collection = new ArrayDeque<>();

    public Deque<T> getCollection() {return collection;}

    public void setCollection(Deque<T> collection) {this.collection = collection;}


}

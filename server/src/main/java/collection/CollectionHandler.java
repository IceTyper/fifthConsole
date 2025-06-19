package collection;

import database.DBHandable;
import database.DBHandler;
import models.SpaceMarine;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class CollectionHandler implements CollectionHandable {
    private static final LocalDateTime creationDate;
    private static final DBHandable db = new DBHandler();
    private static Deque<SpaceMarine> collection = new ArrayDeque<>();
    private static final ReentrantLock lock = new ReentrantLock();

    static {
        creationDate = LocalDateTime.now();
        updateCollection();
    }

    private static void updateCollection() {
        collection = Arrays.stream(db.getSpacemarines())
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    @Override
    public Deque<SpaceMarine> getCollection() {
        lock.lock();
        try {
            return collection;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setCollection(Deque<SpaceMarine> collection) {
        lock.lock();
        try {
            CollectionHandler.collection = collection;
        } finally {
            lock.unlock();
        }
    }

    public long add(SpaceMarine spaceMarine) {
        lock.lock();
        try {
            long id = db.addSpacemarine(spaceMarine);
            if (id != -1) {
                spaceMarine.setId(id);
                collection.add(spaceMarine);
                sortCollection();
            }
            return id;
        } finally {
            lock.unlock();
        }
    }

    public long clear(String username) {
        lock.lock();
        try {
            long rowsAffected = db.clearSpacemarines(username);
            if (rowsAffected > 0) {
                collection.stream().filter(a -> a.getOwner().equals(username))
                        .forEach(collection::remove);
                updateCollection();
                sortCollection();
            }
            return rowsAffected;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(SpaceMarine spaceMarine) {
        lock.lock();
        try {
            boolean removed = db.deleteSpacemarine(spaceMarine);
            if (removed) {
                collection.remove(spaceMarine);
                sortCollection();
            }
            return removed;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean updateElement(SpaceMarine spaceMarine) {
        lock.lock();
        try {
            boolean success = db.updateSpacemarine(spaceMarine);
            if (success) {
                collection.removeIf(a -> Objects.equals(a.getId(), spaceMarine.getId()));
                collection.add(spaceMarine);
                sortCollection();
            }
            return success;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void sortCollection() {
        lock.lock();
        try {
            collection = collection.stream().sorted().collect(Collectors.toCollection(ArrayDeque::new));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return collection.size();
        } finally {
            lock.unlock();
        }
    }
}

package command;

import collection.CollectionHandable;
import collection.CollectionHandler;
import database.DBHandable;
import database.DBHandler;
import exceptions.InvalidStringException;

import java.io.Serializable;
import java.util.Arrays;

public abstract class AbstractCommand implements Serializable {
    protected int[] argNumber;
    protected static final CollectionHandable collectionHandler = new CollectionHandler();

    public AbstractCommand(int[] argNumber) {
        this.argNumber = argNumber;
    }

    public boolean checkArgNumber(int num) {
        return Arrays.stream(argNumber).anyMatch(a -> a == num);
    }

    public abstract Object[] execute(Object[] args) throws InvalidStringException;
}
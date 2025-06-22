package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;

import java.util.Deque;

public class Show extends AbstractCommand implements Helpable {
    public Show(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public Object[] execute(Object[] args) {
        Deque<?> collection = new CollectionHandler().getCollection();
        if (!(collection == null || collection.isEmpty())) {
            return collection.toArray();
        }
        return null;
    }
}


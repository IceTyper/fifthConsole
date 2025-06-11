package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;

import java.util.ArrayList;
import java.util.Deque;

public class Show extends AbstractCommand {
    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<Object>();
        Deque<?> collection = new CollectionHandler().getCollection();
        if (!(collection == null || collection.isEmpty())) {
            for (Object element : collection) {
                response.add("*******");
                response.add(element.toString());
                response.add("*******");
            }
        } else {
            response.add("Коллекция пуста");
        }
        return response.toArray();
    }
}

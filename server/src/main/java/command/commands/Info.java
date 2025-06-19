package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;

import java.util.ArrayList;

public class Info extends AbstractCommand implements Helpable {
    public Info(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "info : вывести информацию о коллекции";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        response.add("PrintMessage");
        CollectionHandler handler = new CollectionHandler();
        response.add("Дата инициализации коллекции: " + handler.getCreationDate());
        response.add("Тип коллекции: " + handler.getCollection().getClass().getSimpleName());
        response.add("Количество элементов в коллекции: " + handler.getCollection().size());
        return response.toArray();
    }
}


package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;

import java.util.ArrayList;

public class Info extends AbstractCommand {
    @Override
    public String getDescription() {
        return "info : вывести информацию о коллекции";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        CollectionHandler handler = new CollectionHandler();
        response.add("Дата инициализации коллекции: " + handler.getCreationDate());
        response.add("Тип коллекции: " + handler.getCollection().getClass().getSimpleName());
        response.add("Количество элементов в коллекции: " + handler.getCollection().size());
        return response.toArray();
    }
}

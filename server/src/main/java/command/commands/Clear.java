package command.commands;

import command.AbstractCommand;
import models.SpaceMarine;
import collection.CollectionHandler;

import java.util.ArrayList;
import java.util.Deque;

public class Clear extends AbstractCommand {
    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        if (collection == null || collection.isEmpty()) {
            response.add("Нечего удалять, а так хотелось :(");
        } else {
            collection.clear();
            response.add("Коллекция успешно очищена");
        }
        return response.toArray();
    }
}

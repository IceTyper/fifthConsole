package commands;

import collection.SpaceMarine;
import collection.utility.CollectionHandler;

import java.util.ArrayList;
import java.util.Deque;

public class Clear extends Command {
    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }

    @Override
    public Object[] execute() {
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

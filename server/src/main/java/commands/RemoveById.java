package commands;

import collection.SpaceMarine;
import collection.utility.CollectionHandler;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Optional;

public class RemoveById extends Command {
    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }

    @Override
    public Object[] execute() {
        ArrayList<Object> response = new ArrayList<>();
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        long id = (long) queue.remove();

        Optional<SpaceMarine> marine = collection.stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if (marine.isPresent()) {
            collection.remove(marine.get());
            response.add("Элемент с id  " + id + " успешно удалён. Сам элемент: " + marine.get());
        } else {
            response.add("Элемента с id " + id + " не существует, хотя так хотелось его испепелить :(");
        }
        return response.toArray();
    }
}

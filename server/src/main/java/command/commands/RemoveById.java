package command.commands;

import command.AbstractCommand;
import command.Helpable;
import connection.User;
import models.SpaceMarine;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Optional;

public class RemoveById extends AbstractCommand implements Helpable {
    public RemoveById(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        Deque<SpaceMarine> collection = collectionHandler.getCollection();
        long id = Long.parseLong((String) args[1]);

        Optional<SpaceMarine> marine = collection.stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if (marine.isPresent()) {
            User user = (User) args[0];
            if (marine.get().getOwner() == null || !marine.get().getOwner().equals(user.username())) {
                response.add("Этот элемент вам не принадлежит");
                return response.toArray();
            }
            if (collectionHandler.remove(marine.get())) {
                collection.remove(marine.get());
                response.add("Элемент с id  " + id + " успешно удалён. Сам элемент: " + marine.get());
            } else {
                response.add("Не удалось удалить элемент с id " + id + ", возможно он уже был удалён.");
            }
        } else {
            response.add("Элемента с id " + id + " не существует, хотя так хотелось его испепелить :(");
        }
        return response.toArray();
    }
}

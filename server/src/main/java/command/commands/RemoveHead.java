package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import connection.User;
import models.SpaceMarine;

import java.util.ArrayList;
import java.util.Deque;

public class RemoveHead extends AbstractCommand implements Helpable {

    public RemoveHead(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "remove_head : вывести первый элемент коллекции и удалить его";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        if (collection == null || collection.isEmpty()) {
            response.add("Элементов нема, а жаль :(");
        } else {
            SpaceMarine first = collection.peekFirst();
            User user = (User) args[0];
            if (first.getOwner() == null || !first.getOwner().equals(user.username())) {
                response.add("Этот элемент вам не принадлежит");
                return response.toArray();
            }
            collectionHandler.remove(first);
            response.add("Удалён первый элемент:");
            response.add(first.toString());
        }
        return response.toArray();
    }
}

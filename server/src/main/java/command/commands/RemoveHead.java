package command.commands;

import command.AbstractCommand;
import models.SpaceMarine;
import collection.CollectionHandler;

import java.util.ArrayList;
import java.util.Deque;

public class RemoveHead extends AbstractCommand {
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
            SpaceMarine first = collection.removeFirst();
            response.add("Удалён первый элемент:");
            response.add(first.toString());
            new CollectionHandler().sortCollection();
        }
        return response.toArray();
    }
}

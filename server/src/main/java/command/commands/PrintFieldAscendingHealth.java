package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import models.SpaceMarine;

import java.util.ArrayList;
import java.util.Deque;

public class PrintFieldAscendingHealth extends AbstractCommand implements Helpable {
    public PrintFieldAscendingHealth(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "print_field_ascending_health : вывести значения поля health всех элементов в порядке возрастания";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        if (collection == null || collection.isEmpty()) {
            response.add("Коллекция пуста");
        } else {
            response.add("Отсортированный список жизней:");
            collection.stream()
                    .map(SpaceMarine::getHealth)
                    .sorted()
                    .map(Object::toString)
                    .forEach(response::add);
        }
        return response.toArray();
    }
}

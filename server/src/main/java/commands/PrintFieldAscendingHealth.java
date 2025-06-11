package commands;

import collection.CollectionHandler;
import models.SpaceMarine;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PrintFieldAscendingHealth extends Command {
    @Override
    public String getDescription() {
        return "print_field_ascending_health : вывести значения поля health всех элементов в порядке возрастания";
    }

    @Override
    public Object[] execute() {
        ArrayList<Object> response = new ArrayList<>();
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        if (collection == null || collection.isEmpty()) {
            response.add("Коллекция пуста");
        } else {
            List<Long> healths = new ArrayList<>();
            for (SpaceMarine marine : collection) {
                healths.add(marine.getHealth());
            }
            healths.sort(Long::compareTo);
            response.add("Отсортированный список жизней:");
            for (Long health : healths) {
                response.add(health.toString());
            }
        }
        return response.toArray();
    }
}

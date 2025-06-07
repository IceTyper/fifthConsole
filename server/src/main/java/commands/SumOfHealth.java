package commands;

import collection.utility.CollectionHandler;
import collection.SpaceMarine;

import java.util.ArrayList;
import java.util.Deque;

public class SumOfHealth extends Command {
    @Override
    public String getDescription() {
        return "sum_of_health : вывести сумму значений поля health для всех элементов коллекции";
    }

    @Override
    public Object[] execute() {
        ArrayList<Object> response = new ArrayList<>();
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        if (collection == null || collection.isEmpty()) {
            response.add("Элементов нема");
        } else {
            long sum = 0L;
            for (SpaceMarine marine : collection) {
                sum += marine.getHealth();
            }
            response.add("Сумма значений поля health: " + sum);
        }
        return response.toArray();
    }
}

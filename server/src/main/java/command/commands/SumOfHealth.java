package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import models.SpaceMarine;

import java.util.Deque;

public class SumOfHealth extends AbstractCommand implements Helpable {
    public SumOfHealth(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "sum_of_health : вывести сумму значений поля health для всех элементов коллекции";
    }

    @Override
    public Object[] execute(Object[] args) {
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        if (collection == null || collection.isEmpty()) {
            return new Object[]{"Элементов нема"};
        }
        long sum = collection.stream().mapToLong(SpaceMarine::getHealth).sum();
        return new Object[]{("Сумма значений поля health: " + sum)};

    }
}

package commands;

import important.Core;
import interfaces.Command;
import models.SpaceMarine;

import java.util.Deque;

public class RemoveLower implements Command {
    @Override
    public String getDescription() {
        return "remove_lower {element} - удаление из коллекции всех элементов, " +
                "меньше заданного";
    }

    @Override
    public void execute(Core core, String[] args) {
        Deque<SpaceMarine> arrDeq = core.getCollectionManager().getCollection();
        SpaceMarine comparingMarine = core.getBuilder().buildSpacemarine();
        arrDeq.removeIf(spaceMarine -> comparingMarine.compareTo(spaceMarine) > 0);
        System.out.println("Элементы, меньшие, чем заданный, изничтожены");
    }
}

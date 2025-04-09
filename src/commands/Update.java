package commands;
import important.Core;
import interfaces.Command;
import models.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Update implements Command {
    @Override
    public String getDescription() {
        return "update {id} {element} - обновление значения элемента коллекции, " +
                "id которого равен заданному";
    }

    @Override
    public void execute(Core core, String[] args) {
        if (args.length == 1) {
            System.out.println("Вы не ввели айдишник");
        } else {
            try {
                int id = Integer.parseInt(args[1]);
                Deque<SpaceMarine> collection = core.getCollectionManager().getCollection();
                boolean isFound = false;
                for (SpaceMarine spaceMarine : collection) {
                    if (spaceMarine.getId() == id) {
                        isFound = true;
                        List<SpaceMarine> list = new ArrayList<>(collection);
                        SpaceMarine updatedSpaceMarine = core.getBuilder().buildSpacemarine();
                        list.set(list.indexOf(spaceMarine), updatedSpaceMarine);
                        collection = new ArrayDeque<>(list);
                        core.getIOManager().printMessage("Космический корабль успешно добавлен в коллекцию!");
                    }
                }
                if (!isFound) {
                    System.out.println("Корабля с данным ID не существует");
                }
            } catch (NumberFormatException e) {
                core.getIOManager().printError("Айдишник криво введён\n");
            }
        }
    }
}

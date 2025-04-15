package org.example.commands;

import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

public class RemoveById implements Command {
    @Override
    public String getDescription() {
        return "remove_by_id - удаление элемента из коллекции по его id";
    }

    @Override
    public void execute(Core core, String[] args) {
        if (args.length <= 1) {
            System.out.println("ID не получен");
        } else {
            try {
                Long id = Long.parseLong(args[1]);
                boolean flag = false;
                CollectionManager<SpaceMarine> cManager = core.getCollectionManager();
                for (SpaceMarine spaceMarine : cManager.getCollection()) {
                    if (spaceMarine.getId().equals(id)) {
                        flag = true;
                        cManager.getCollection().remove(spaceMarine);
                        cManager.sortCollection();
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Элемент успешно удалён");
                } else {
                    System.out.println("Элемент не найден");
                }
            } catch (NumberFormatException e) {
                System.out.println("Id у вас не числовое какое-то");
            }
        }

    }
}

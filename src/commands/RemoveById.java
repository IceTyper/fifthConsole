package commands;

import important.Core;
import interfaces.Command;

public class RemoveById implements Command {
    @Override
    public String getDescription() {
        return "remove_by_id - удаление элемента из коллекции по его id";
    }

    @Override
    public void execute(Core core, String[] args) {
        System.out.println("Remove by id");
    }
}

package org.example.commands;

import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

public class Add implements Command {
    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    @Override
    public void execute(Core core, String[] args) {
        SpaceMarine spaceMarine = core.getBuilder().buildSpacemarine();
        core.getCollectionManager().addElement(spaceMarine);
        core.getIOManager().printMessage("Космический корабль успешно добавлен в коллекцию!");
    }
}

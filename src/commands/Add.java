package commands;

import important.Core;
import models.SpaceMarine;

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

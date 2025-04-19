package org.example.important;

import org.example.interfaces.Command;
import org.example.interfaces.IOManagable;
import org.example.models.SpaceMarine;

public class Core {

    private boolean isOn = true;
    private final CommandManager commandManager;
    private final CollectionManager<SpaceMarine> collectionManager;
    private final IOManagable ioManager;
    private final Builder builder;

    public Core(CommandManager commandManager, CollectionManager<SpaceMarine> collectionManager, IOManagable ioManager, Builder builder) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
        this.builder = builder;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CollectionManager<SpaceMarine> getCollectionManager() {
        return collectionManager;
    }

    public IOManagable getIOManager() {
        return ioManager;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void startCore() {
        ioManager.printMessage("Добро пожаловать в мою харчевню.\nДля списка команд напишите help");
        while (isOn) {
            Command command = ioManager.checkInputForCommand(this);
            command.execute(this, ioManager.getUserInputInstance().split(" "));
        }
    }

    public void endCore() {
        isOn = false;
    }
}

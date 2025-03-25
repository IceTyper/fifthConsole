package important;

import commands.Command;
import models.SpaceMarine;

public class Core {

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

    public CommandManager getCommandManager() {return commandManager;}
    public CollectionManager<SpaceMarine> getCollectionManager() {return collectionManager;}
    public IOManagable getIOManager() {return ioManager;}
    public Builder getBuilder() {return builder;}

    public void startCore() {
        ioManager.printMessage("Добро пожаловать в мою харчевню.\nДля списка команд напишите help");
        while (true) {
            Command command = ioManager.checkInputForCommand(this);
            command.execute(this);
        }

    }
}

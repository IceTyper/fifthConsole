package important;

import models.SpaceMarine;

public class Core {

    private final CommandManager commandManager;
    private final CollectionManager<SpaceMarine> collectionManager;
    private final IOManagable ioManager;

    public Core(CommandManager commandManager, CollectionManager<SpaceMarine> collectionManager, IOManagable ioManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
    }

    public CommandManager getCommandManager() {return commandManager;}
    public CollectionManager<SpaceMarine> getCollectionManager() {return collectionManager;}
    public IOManagable getIOManager() {return ioManager;}

}

package Managers;

import models.SpaceMarine;

public class Core {

    private final CommandManager commandManager;
    private final CollectionManager<SpaceMarine> collectionManager;

    public Core(CommandManager commandManager, CollectionManager<SpaceMarine> collectionManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
    }

    public CommandManager getCommandManager() {return commandManager;}
    public CollectionManager<SpaceMarine> getCollectionManager() {return collectionManager;}
}

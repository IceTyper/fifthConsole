package org.example.important;

import org.example.interfaces.Command;
import org.example.interfaces.FileManagable;
import org.example.interfaces.IOManagable;
import org.example.models.SpaceMarine;

import java.io.File;

public class Core {

    private boolean isOn = true;
    private final CommandManager commandManager;
    private final CollectionManager<SpaceMarine> collectionManager;
    private final IOManagable ioManager;
    private final Builder builder;
    private final FileManagable fileManager;

    public Core(CommandManager commandManager, CollectionManager<SpaceMarine> collectionManager, IOManagable ioManager, Builder builder, FileManagable fileManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
        this.builder = builder;
        this.fileManager = fileManager;
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

    public FileManagable getFileManager() {return fileManager;}

    public void startCore() {
        //fileManager.readFromFile(new File("C:/Users/fmusa/IdeaProjects/fifthConsole/src/main/resources/collection.json"), collectionManager);
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

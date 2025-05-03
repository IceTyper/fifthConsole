package org.example.important;

import org.example.interfaces.Command;
import org.example.interfaces.FileManagable;
import org.example.interfaces.IOManagable;
import org.example.models.SpaceMarine;

import java.io.File;
import java.util.Scanner;

public class Core {

    private final CommandManager commandManager;
    private final CollectionManager collectionManager;
    private final IOManagable ioManager;
    private final Builder builder;
    private final FileManagable fileManager;
    private boolean isOn = true;

    public Core(CommandManager commandManager, CollectionManager collectionManager, IOManagable ioManager, Builder builder, FileManagable fileManager) {
        this.commandManager = commandManager;
        this.collectionManager = collectionManager;
        this.ioManager = ioManager;
        this.builder = builder;
        this.fileManager = fileManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public IOManagable getIOManager() {
        return ioManager;
    }

    public Builder getBuilder() {
        return builder;
    }

    public FileManagable getFileManager() {
        return fileManager;
    }

    public void startCore(String[] args) {
        if (args.length == 1) {
            if (args[0].endsWith(".json")) {
                if (args[0].contains("/")) {
                    System.out.println("Не используйте / в названии (или не создавайте новых папок)");
                } else {
                    fileManager.readFromFile(new File("C:/Users/fmusa/IdeaProjects/fifthConsole/src/main/resources/" + args[0]), collectionManager);
                    long num = collectionManager.getCollection().size();
                    SpaceMarine.setID(num);
                }
            }
        }

        ioManager.printMessage("Добро пожаловать в мою харчевню.\nДля списка команд напишите help");
        while (isOn) {
            Command command = null;
            Scanner scanner = new Scanner(System.in);
            while (command == null) {
                ioManager.setUserInputInstance(scanner.nextLine().toLowerCase());
                command = ioManager.checkInputForCommand(this);
                if (command == null) {
                    System.out.println("Строка не опознана, повторите попытку.\n");
                }
            }
            command.execute(this, scanner, ioManager.getUserInputInstance().split(" "));
        }
    }

    public void endCore() {
        isOn = false;
    }
}

package org.example;

import org.example.commands.*;
import org.example.important.*;
import org.example.interfaces.FileManagable;
import org.example.interfaces.IOManagable;

import java.io.IOException;
import java.util.ArrayDeque;


public class Main {
    public static void main(String[] args) throws IOException {
        CommandManager commandManager = new CommandManager();
        CollectionManager collectionManager = new CollectionManager(new ArrayDeque<>());
        IOManagable ioManager = new IOManager();
        FileManagable fileManager = new FileManager();
        commandManager.addCommands(new Add(), new AddIfMax(), new Clear(),
                new ExecuteScript(), new Exit(), new FilterGreaterThanMeleeWeapon(),
                new Help(), new Info(), new PrintFieldAscendingHealth(), new RemoveById(),
                new RemoveHead(), new RemoveLower(), new Save(), new Show(),
                new SumOfHealth(), new Update());
        Core core = new Core(commandManager, collectionManager, ioManager, new Builder(ioManager), fileManager);
        String[] fileName = {"collection.json"};
        if (args.length > 0) {
            core.startCore(args);
        } else {
            core.startCore(fileName);
        }
    }
}

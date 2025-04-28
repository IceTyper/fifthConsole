package org.example;

import org.example.commands.*;
import org.example.important.*;
import org.example.interfaces.FileManagable;
import org.example.interfaces.IOManagable;

import java.util.ArrayDeque;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        System.out.println("введено: " + Arrays.toString(args));
        /*Weapon weapon = Weapon.HEAVY_FLAMER;
        Coordinates coords = new Coordinates((long) 5, 5);
        Deque<SpaceMarine> arrDeq = new ArrayDeque<>();
        SpaceMarine ship = new SpaceMarine((long) 1, "abobus", coords, LocalDate.now(), (long) 100500, true, weapon, MeleeWeapon.LIGHTING_CLAW, new Chapter("Eha", 1, "Angedonia"));
        arrDeq.add(ship);
        System.out.println(ship);
        System.out.println(arrDeq);*/
        CommandManager commandManager = new CommandManager();
        commandManager.addCommands(new Add(), new AddIfMax(), new Clear(),
                new ExecuteScript(), new Exit(), new FilterGreaterThanMeleeWeapon(),
                new Help(), new Info(), new PrintFieldAscendingHealth(), new RemoveById(),
                new RemoveHead(), new RemoveLower(), new Save(), new Show(),
                new SumOfHealth(), new Update());
        CollectionManager collectionManager = new CollectionManager(new ArrayDeque<>());
//        collectionManager.getCollection().add(new SpaceMarine("sg", new Coordinates((long) 1, 1), LocalDate.now(), (long) 1, true, Weapon.GRAV_GUN, MeleeWeapon.CHAIN_SWORD, new Chapter("wef", 232, "erwger")));
        IOManagable ioManager = new IOManager();
        FileManagable fileManager = new FileManager();
        Core core = new Core(commandManager, collectionManager, ioManager, new Builder(ioManager), fileManager);
//        new File("C:/Users/fmusa/IdeaProjects/fifthConsole/src/main/resources/collection.json").delete();
        String[] fileName = {"collection.json"};
        if (args.length > 0) {
            core.startCore(args);
        } else {
            core.startCore(fileName);
        }
    }
}

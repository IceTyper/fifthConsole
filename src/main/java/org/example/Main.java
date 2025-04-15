package org.example;

import org.example.important.*;
import org.example.interfaces.*;
import org.example.models.*;
import org.example.commands.*;

import java.time.LocalDate;
import java.util.ArrayDeque;


public class Main {
    public static void main(String[] args) {
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
        CollectionManager<SpaceMarine> collectionManager = new CollectionManager<>(new ArrayDeque<>());
        collectionManager.getCollection().add(new SpaceMarine( "sg", new Coordinates((long) 1, 1 ), LocalDate.now(), (long) 1, true, Weapon.GRAV_GUN, MeleeWeapon.CHAIN_SWORD, new Chapter("wef", 232, "erwger")));
        IOManagable ioManager = new IOManager();
        Core core = new Core(commandManager, collectionManager, ioManager, new Builder(ioManager));
        core.startCore();
    }
}

import important.CollectionManager;
import important.CommandManager;
import important.Core;
import commands.*;
import models.SpaceMarine;

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
        CollectionManager<SpaceMarine> collectionManager = new CollectionManager<>(new ArrayDeque<SpaceMarine>());
        Core core = new Core(commandManager, collectionManager);
        System.out.println(commandManager);
        core.getCommandManager().getCommand("help").execute(core);

    }
}

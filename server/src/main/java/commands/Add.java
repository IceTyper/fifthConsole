package commands;

import collection.*;
import collection.utility.CollectionControllable;
import collection.utility.CollectionHandler;

import java.util.ArrayDeque;

public class Add extends Command {
    private static CollectionControllable handler = new CollectionHandler();

    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    //name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world
    @Override
    public Object[] execute() {
        String name = (String) queue.remove();
        long x = (long) queue.remove();
        int y = (int) queue.remove();
        long health = (long) queue.remove();
        boolean loyal = (boolean) queue.remove();
        Weapon weapon = Weapon.getWeapon((String) queue.remove());
        MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeapon((String) queue.remove());
        String chapterName = (String) queue.remove();
        long marinesCount = (long) queue.remove();
        String world = (String) queue.remove();
        ArrayDeque<SpaceMarine> collection = (ArrayDeque<SpaceMarine>) handler.getCollection();
        int size = collection.size();
        long id = handler.getMaxId();
        handler.getCollection().addFirst(new SpaceMarine(id + 1, name, new Coordinates(x, y), health, loyal, weapon, meleeWeapon, new Chapter(chapterName, marinesCount, world)));
        return !(size == collection.size()) ? new Object[]{"Элемент успешно добавлен в коллекцию! ID элемента: " + id} : new Object[]{"Добавление элемента провалилось"};
    }
}

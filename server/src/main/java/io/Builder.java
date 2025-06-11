package io;

import collection.CollectionControllable;
import collection.CollectionHandler;
import models.*;

import java.util.Queue;

public class Builder {


    public SpaceMarine buildSpaceMarine(Queue<Object> queue) {
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
        CollectionControllable handler = new CollectionHandler();
        long id = handler.getMaxId();
        return new SpaceMarine(id + 1, name, new Coordinates(x, y), health, loyal, weapon, meleeWeapon, new Chapter(chapterName, marinesCount, world));
    }
}

package io;

import models.*;

import java.time.LocalDateTime;

public class Builder {

    public static SpaceMarine buildSpaceMarine(Object[] args) {
        String name = (String) args[0];
        long x = (long) args[1];
        int y = (int) args[2];
        long health = (long) args[3];
        boolean loyal = (boolean) args[4];
        Weapon weapon = Weapon.getWeapon((String) args[5]);
        MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeapon((String) args[6]);
        String chapterName = (String) args[7];
        long marinesCount = (long) args[8];
        String world = (String) args[9];
        String owner = (String) args[10];
        if (args.length == 12 && args[11] instanceof LocalDateTime time) {
            return new SpaceMarine(name, new Coordinates(x, y), health, loyal, weapon, meleeWeapon,
                    new Chapter(chapterName, marinesCount, world), owner, time);
        } else {
            return new SpaceMarine(name, new Coordinates(x, y), health, loyal, weapon, meleeWeapon,
                    new Chapter(chapterName, marinesCount, world), owner);
        }
    }
}

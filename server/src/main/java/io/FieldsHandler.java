package io;

import models.MeleeWeapon;
import models.Weapon;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

import static io.Validation.*;

public class FieldsHandler {

    // -483, -325
    //name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world
    public static boolean checkFields(Object args[]) {
        if (args == null || args.length != 10) return false;
        try {
            return validateString(a -> a != null, (String) args[0])
                    && validateNumber(a -> a > -483, getNumber(Long::parseLong, (String) args[1]))
                    && validateNumber(a -> a > -325, getNumber(Integer::parseInt, (String) args[2]))
                    && validateNumber(a -> a > 0, getNumber(Long::parseLong, (String) args[3]))
                    && validateString(a -> a.equals("false") || a.equals("true"), (String) args[4])
                    && validateString(a -> Weapon.getWeapon(a.toUpperCase()) != null, (String) args[5])
                    && validateString(a -> MeleeWeapon.getMeleeWeapon(a.toUpperCase()) != null, (String) args[6])
                    && validateString(Objects::nonNull, (String) args[7])
                    && validateNumber(a -> a > 0 && a <= 1000, getNumber(Long::parseLong, (String) args[8]))
                    && validateString(Objects::nonNull, (String) args[9]);
        } catch (Exception e) {
            return false;
        }
    }
}

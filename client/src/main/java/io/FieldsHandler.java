package io;

import models.MeleeWeapon;
import models.Weapon;

import java.io.PrintStream;
import java.util.Objects;


public class FieldsHandler {
    private static final Validatable val = new Validator();
    private static PrintStream out = System.out;

    public static Object[] recordSpacemarineFields() {
        out.println("Введите имя (непустая строка): ");
        String name = val.validateString(a -> a != null && !a.trim().isEmpty());
        out.println("Введите координату x (целое число x > -483): ");
        String x = val.validateNumber(Long::parseLong, a -> (a != null && a > -483));
        out.println("Введите координату y (целое число y > -325): ");
        String y = val.validateNumber(Integer::parseInt, a -> (a != null && a > -325));
        out.println("Введите здоровье (целое число и > 0): ");
        String health = val.validateNumber(Long::parseLong, a -> (a != null && a > 0));
        out.println("Введите лояльность корабля (true или false): ");
        String loyal = val.validateString(a -> (Objects.equals(a, "false") || Objects.equals(a, "true")));

        out.println("Выберите оружие, не обязательно заглавными (HEAVY_FLAMER, HEAVY_BOLT_GUN, GRAV_GUN, NONE): ");
        String weaponType = val.validateString(a -> Weapon.getWeapon(a.toUpperCase()) != null).toUpperCase();

        out.println("Выберите холодное оружие, не обязательно заглавными (CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST, NONE): ");
        String meleeWeaponType = val.validateString(a -> MeleeWeapon.getMeleeWeapon(a.toUpperCase()) != null).toUpperCase();

        out.println("Постройте Chapter, введите название (непустая строка): ");
        String chapterName = val.validateString(a -> (a != null && !a.trim().isEmpty()));
        out.println("Введите количество людей в экипаже (целое число, > 0 и <= 1000): ");
        String marinesCount = val.validateNumber(Long::parseLong, a -> (a != null && a > 0 && a <= 1000));
        out.println("Введите название мира (непустая строка): ");
        String world = val.validateString(a -> (!a.trim().isEmpty()));

        return new Object[]{name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world};
    }
}



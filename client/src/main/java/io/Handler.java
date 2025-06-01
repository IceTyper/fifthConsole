package io;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Handler {
    private static PrintStream out = System.out;
    private final IO io = IOController.getInstance();
    private final ExceptionHandlingValidator val = new ExceptionHandlingValidator();

    public Object[] recordSpacemarineFields() {
        out.println("Введите имя: ");
        String name = val.validateString(a -> a != null && !a.trim().isEmpty());
        out.println("Введите координаты:");
        out.println("Введите координату x (x > -483): ");
        long x = val.validateDigit(Long::parseLong, a -> (a != null && a > -483));
        out.println("Введите координату y (y > -325): ");
        int y = val.validateDigit(Integer::parseInt, a -> (a != null && a > -325));
        out.println("Введите здоровье (>0): ");
        Long health = val.validateDigit(Long::parseLong, a -> (a != null && a > 0));
        out.println("Введите лояльность корабля (true или false): ");
        boolean loyal = Boolean.parseBoolean(val.validateString(a -> (Objects.equals(a, "false") || Objects.equals(a, "true"))));

        out.println("Выберите оружие, не обязательно заглавными (HEAVY_FLAMER, HEAVY_BOLT_GUN, GRAV_GUN, NONE): ");
        List<String> weapons = Arrays.asList("HEAVY_FLAMER", "HEAVY_BOLT_GUN", "GRAV_GUN", "NONE");
        String weaponType = val.validateString(a -> weapons.contains(a.toUpperCase())).toUpperCase();

        out.println("Выберите холодное оружие, не обязательно заглавными (CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST, NONe): ");
        List<String> meleeWeapons = Arrays.asList("CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST", "NONE");
        String meleeWeaponType = val.validateString(a -> meleeWeapons.contains(a.toUpperCase())).toUpperCase();

        out.println("Постройте Chapter, введите название: ");
        String chapterName = val.validateString(a -> (a != null && !a.trim().isEmpty()));
        out.println("Введите количество людей в экипаже: ");
        long marinesCount = val.validateDigit(Long::parseLong, a -> (a != null && a > 0 && a <= 1000));
        out.println("Введите название мира: ");
        String world = val.validateString(a -> (!a.trim().isEmpty()));

        return new Object[]{name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world};
    }
}



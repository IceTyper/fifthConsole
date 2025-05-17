package client.io;

import java.io.PrintStream;
import java.util.*;

import static client.io.ExceptionHandlingValidator.validateDigit;
import static client.io.ExceptionHandlingValidator.validateString;


public class Handler {
    private static PrintStream out = System.out;
    private final IOController io = IOController.getInstance();

    public static Queue<Object> recordSpacemarineFields() {
        out.println("Введите имя: ");
        String name = validateString(a -> a != null && !a.trim().isEmpty());
        out.println("Введите координаты:");
        out.println("Введите координату x (x > -483): ");
        long x = validateDigit(Long::parseLong, a -> (a != null && a > -483));
        out.println("Введите координату y (y > -325): ");
        int y = validateDigit(Integer::parseInt, a -> (a != null && a > -325));
        out.println("Введите здоровье (>0): ");
        Long health = validateDigit(Long::parseLong, a -> (a != null && a > 0));
        out.println("Введите лояльность корабля (true или false): ");
        boolean loyal = Boolean.parseBoolean(validateString(a -> (Objects.equals(a, "false") || Objects.equals(a, "true"))));

        out.println("Выберите оружие, не обязательно заглавными (HEAVY_FLAMER, HEAVY_BOLT_GUN, GRAV_GUN, NONE): ");
        List<String> weapons = Arrays.asList("HEAVY_FLAMER", "HEAVY_BOLT_GUN", "GRAV_GUN", "NONE");
        String weaponType = validateString(a -> weapons.contains(a.toUpperCase())).toUpperCase();

        out.println("Выберите холодное оружие, не обязательно заглавными (CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST, NONe): ");
        List<String> meleeWeapons = Arrays.asList("CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST", "NONE");
        String meleeWeaponType = validateString(a -> meleeWeapons.contains(a.toUpperCase())).toUpperCase();

        out.println("Постройте Chapter, введите название: ");
        String chapterName = validateString(a -> (a != null && !a.trim().isEmpty()));
        out.println("Введите количество людей в экипаже: ");
        long marinesCount = validateDigit(Long::parseLong, a -> (a != null && a > 0 && a <= 1000));
        out.println("Введите название мира: ");
        String world = validateString(a -> (!a.trim().isEmpty()));


        return new LinkedList<>(Arrays.asList(name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world));
    }
}



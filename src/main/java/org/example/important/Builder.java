package org.example.important;
import org.example.interfaces.IOManagable;
import org.example.models.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Builder {


    private final IOManagable ioManager;

    public Builder(IOManagable ioManager) {this.ioManager = ioManager;}

    public SpaceMarine buildSpacemarine() {
        ioManager.printMessage("Введите имя космического корабля: ");
        String name = ioManager.validateString(a -> (a != null && !a.trim().isEmpty()));
        ioManager.printMessage("Теперь введите координаты: ");
        Coordinates coords = buildCoordinates();
        ioManager.printMessage("Сколько жизней у вашего корабля? Зафиксируйте (здоровье > 0): ");
        Long health = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > 0));
        ioManager.printMessage("Введите лояльность корабля (true или false): ");
        boolean loyal = Boolean.parseBoolean(ioManager.validateString(a -> (Objects.equals(a, "false") || Objects.equals(a, "true"))));
        ioManager.printMessage("Выберите оружие (HEAVY_FLAMER, HEAVY_BOLT_GUN, GRAV_GUN): ");
        Weapon weaponType = buildWeapon();
        ioManager.printMessage("Выберите холодное оружие (CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST): ");
        MeleeWeapon meleeWeapon = buildMeleeWeapon();
        Chapter chapter = buildChapter();
        return new SpaceMarine(name, coords, LocalDate.now(), health, loyal, weaponType, meleeWeapon, chapter);
    }

    public Coordinates buildCoordinates() {
        ioManager.printMessage("Введите координату x (x > -483): ");
        long x = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > -483));
        ioManager.printMessage("Введите координату y (y > -325): ");
        int y = ioManager.validateDigit(Integer::parseInt, a -> (a != null && a > -325));
        return new Coordinates(x, y);
    }

    public Chapter buildChapter() {
        ioManager.printMessage("Введите название части: ");
        String name = ioManager.validateString(a -> (a != null && !a.trim().isEmpty()));
        ioManager.printMessage("Теперь введи количество людей в экипаже: ");
        long marinesCount = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > 0 && a <= 1000));
        ioManager.printMessage("Введи название мира: ");
        String world = ioManager.validateString(a -> (!a.trim().isEmpty()));
        return new Chapter(name, marinesCount, world);
    }

    public Weapon buildWeapon() {
        List<String> weapons = Arrays.asList("HEAVY_FLAMER", "HEAVY_BOLT_GUN", "GRAV_GUN", "NONE");
        String weaponType = ioManager.validateString(a -> weapons.contains(a.toUpperCase()));
        return Weapon.getWeapon(weaponType);
    }

    public MeleeWeapon buildMeleeWeapon() {
        List<String> meleeWeapons = Arrays.asList("CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST", "NONE");
        String meleeWeaponType = ioManager.validateString(a -> meleeWeapons.contains(a.toUpperCase()));
        return MeleeWeapon.getMeleeWeapon(meleeWeaponType);
    }
}

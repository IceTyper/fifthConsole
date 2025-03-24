package important;
import important.IOManager;
import models.*;

import java.util.Objects;


public class Builder {
    static private long ID = 1;

    private final IOManager ioManager;

    public Builder(IOManager ioManager) {this.ioManager = ioManager;}

    public SpaceMarine buildSpacemarine() {
        Long id = ID++;
        ioManager.printMessage("Введите имя космического корабля: ");
        String name = ioManager.validate(a -> (a != null && !a.trim().isEmpty()));
        ioManager.printMessage("Теперь введите координаты: ");
        Coordinates coords = buildCoordinates();
        ioManager.printMessage("Сколько жизней у вашего корабля? Зафиксируйте (здоровье > 0): ");
        Long health = ioManager.validateDigit(a -> (a != null && a > 0));
        boolean loyalty = Boolean.parseBoolean(ioManager.validate(a -> (Objects.equals(a, "false") || Objects.equals(a, "true"))));
        Weapon weaponType = buildWeapon();
        MeleeWeapon meleeWeapon = buildMeleeWeapon();
        Chapter chapter = buildChapter();
    }

    public Coordinates buildCoordinates() {
        return new Coordinates((long)1, 2);
    }

    public Chapter buildChapter() {
        return new Chapter("v", (long) 1, "r");
    }

    public Weapon buildWeapon() {
        return Weapon.GRAV_GUN
    }

    public MeleeWeapon buildMeleeWeapon() {
        return MeleeWeapon.CHAIN_SWORD
    }
}

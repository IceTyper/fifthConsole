package models;

import java.io.Serializable;

public enum Weapon implements Serializable {
    HEAVY_BOLTGUN("HEAVY_BOLT_GUN"),
    GRAV_GUN("GRAV_GUN"),
    HEAVY_FLAMER("HEAVY_FLAMER"),
    NOTHING("NONE");

    private final String name;

    Weapon(String name) {
        this.name = name;
    }

    public static Weapon getWeapon(String name) {
        return java.util.Arrays.stream(values())
                .filter(weapon -> weapon.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

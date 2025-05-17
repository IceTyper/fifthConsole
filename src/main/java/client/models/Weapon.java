package client.models;

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
        for (Weapon weapon : values()) {
            if (weapon.getName().equals(name)) {
                return weapon;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

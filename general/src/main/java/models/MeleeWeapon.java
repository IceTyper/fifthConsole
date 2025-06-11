package models;

import java.io.Serializable;

public enum MeleeWeapon implements Serializable {
    CHAIN_SWORD("CHAIN_SWORD"),
    LIGHTING_CLAW("LIGHTING_CLAW"),
    POWER_FIST("POWER_FIST"),
    NOTHING("NONE");

    private final String name;

    MeleeWeapon(String name) {
        this.name = name;
    }

    public static MeleeWeapon getMeleeWeapon(String name) {
        for (MeleeWeapon weapon : MeleeWeapon.values()) {
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

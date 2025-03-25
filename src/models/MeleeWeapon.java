package models;

public enum MeleeWeapon {
    CHAIN_SWORD("CHAIN_SWORD"),
    LIGHTING_CLAW("LIGHTING_CLAW"),
    POWER_FIST("POWER_FIST");

    private final String name;

    MeleeWeapon(String name) {
        this.name = name;
    }

    public static MeleeWeapon getMeleeWeapon(String name) {
        for (MeleeWeapon weapon : values()) {
            if (weapon.name.equals(name)) {
                return weapon;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}

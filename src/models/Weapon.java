package models;

public enum Weapon {
    HEAVY_BOLTGUN("HEAVY_BOLT_GUN"),
    GRAV_GUN("GRAV_GUN"),
    HEAVY_FLAMER("HEAVY_FLAMER"),
    NOTHING("");

    private final String name;

    Weapon(String name) {
        this.name = name;
    }

    public static Weapon getWeapon(String name)  {
        for (Weapon weapon : values()) {
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

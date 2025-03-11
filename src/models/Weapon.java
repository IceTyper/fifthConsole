package models;

public enum Weapon {
    HEAVY_BOLTGUN("BoltGun"),
    GRAV_GUN("GravGun"),
    HEAVY_FLAMER("Flamer");

    private String name;

    Weapon(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

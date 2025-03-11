package models;

public enum MeleeWeapon {
    CHAIN_SWORD("ChainSword"),
    LIGHTING_CLAW("LightingClaw"),
    POWER_FIST("PowerFist"),;

    private String name;

    MeleeWeapon(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

import models.*;

import java.time.LocalDate;



public class Main {

    public static void main(String[] args) {
        Weapon weapon = Weapon.HEAVY_FLAMER;
        Coordinates coords = new Coordinates((long) 5, 5);
        SpaceMarine ship = new SpaceMarine((long) 1, "abobus", coords, LocalDate.now(), (long) 100500, true, weapon, MeleeWeapon.LIGHTING_CLAW, new Chapter("Eha", 1, "Angedonia"));
        System.out.println(ship);
    }
}

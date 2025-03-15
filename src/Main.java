import models.*;

import java.time.LocalDate;
import java.util.*;


public class Main {

    public static void main(String[] args) {
        Weapon weapon = Weapon.HEAVY_FLAMER;
        Coordinates coords = new Coordinates((long) 5, 5);
        Deque<SpaceMarine> arrDeq = new ArrayDeque<>();
        SpaceMarine ship = new SpaceMarine((long) 1, "abobus", coords, LocalDate.now(), (long) 100500, true, weapon, MeleeWeapon.LIGHTING_CLAW, new Chapter("Eha", 1, "Angedonia"));
        arrDeq.add(ship);

        System.out.println(ship);
        System.out.println(arrDeq);

    }
}

package org.example.important;

import org.example.interfaces.IOManagable;
import org.example.models.*;

import java.time.LocalDate;
import java.util.*;


public class Builder {

    private final IOManagable ioManager;

    public Builder(IOManagable ioManager) {
        this.ioManager = ioManager;
    }

    public SpaceMarine buildSpacemarine(Scanner scanner) {
        try {
            ioManager.printMessage("Введите имя космического корабля: ");
            ioManager.setUserInputInstance(scanner.nextLine());
            String name = ioManager.validateString(a -> (a != null && !a.trim().isEmpty()));
            ioManager.printMessage("Теперь введите координаты: ");
            Coordinates coords = buildCoordinates(scanner);
            ioManager.printMessage("Сколько жизней у вашего корабля? Зафиксируйте (здоровье > 0): ");
            ioManager.setUserInputInstance(scanner.nextLine());
            Long health = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > 0));
            ioManager.printMessage("Введите лояльность корабля (true или false): ");
            ioManager.setUserInputInstance(scanner.nextLine());
            boolean loyal = Boolean.parseBoolean(ioManager.validateString(a -> (Objects.equals(a, "false") || Objects.equals(a, "true"))));
            ioManager.printMessage("Выберите оружие (HEAVY_FLAMER, HEAVY_BOLT_GUN, GRAV_GUN): ");
            Weapon weaponType = buildWeapon(scanner);
            ioManager.printMessage("Выберите холодное оружие (CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST): ");
            MeleeWeapon meleeWeapon = buildMeleeWeapon(scanner);
            Chapter chapter = buildChapter(scanner);
            return new SpaceMarine(name, coords, LocalDate.now(), health, loyal, weaponType, meleeWeapon, chapter);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /*public SpaceMarine buildSpaceMarine(BufferedReader bufferedReader) throws IOException {
        while (bufferedReader.ready()) {
            IOManagable ioManager = new IOManager();
            ioManager.setUserInputInstance(bufferedReader.readLine());
            String name = ioManager.validateString(a -> (a != null && !a.trim().isEmpty()));
            ioManager.setUserInputInstance(bufferedReader.readLine());
            long x = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > -483));
            int y = ioManager.validateDigit(Integer::parseInt, a -> (a != null && a > -325));
            Coordinates coords = new Coordinates(x, y);
            ioManager.setUserInputInstance(bufferedReader.readLine());
            Long health = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > 0));
            ioManager.setUserInputInstance(bufferedReader.readLine());
            boolean loyal = Boolean.parseBoolean(ioManager.validateString(a -> (Objects.equals(a, "false") || Objects.equals(a, "true"))));
            ioManager.setUserInputInstance(bufferedReader.readLine());
            Weapon weaponType = buildWeapon();
            ioManager.setUserInputInstance(bufferedReader.readLine());
            MeleeWeapon meleeWeapon = buildMeleeWeapon();
            ioManager.setUserInputInstance(bufferedReader.readLine());
            String chapterName = ioManager.validateString(a -> (a != null && !a.trim().isEmpty()));
            long marinesCount = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > 0 && a <= 1000));
            String world = ioManager.validateString(a -> (!a.trim().isEmpty()));
            Chapter chapter = new Chapter(chapterName, marinesCount, world);
            return new SpaceMarine(name, coords, LocalDate.now(), health, loyal, weaponType, meleeWeapon, chapter);
        }
    }*/

    public void updateSpacemarine(SpaceMarine oldSpaceMarine, Scanner scanner) {
        ioManager.printMessage("Введите имя космического корабля: ");
        ioManager.setUserInputInstance(scanner.nextLine());
        String name = ioManager.validateString(a -> (a != null && !a.trim().isEmpty()));
        oldSpaceMarine.setName(name);
        ioManager.printMessage("Теперь введите координаты: ");
        Coordinates coords = buildCoordinates(scanner);
        oldSpaceMarine.setCoordinates(coords);
        ioManager.printMessage("Сколько жизней у вашего корабля? Зафиксируйте (здоровье > 0): ");
        ioManager.setUserInputInstance(scanner.nextLine());
        Long health = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > 0));
        oldSpaceMarine.setHealth(health);
        ioManager.printMessage("Введите лояльность корабля (true или false): ");
        ioManager.setUserInputInstance(scanner.nextLine());
        boolean loyal = Boolean.parseBoolean(ioManager.validateString(a -> (Objects.equals(a, "false") || Objects.equals(a, "true"))));
        oldSpaceMarine.setLoyal(loyal);
        ioManager.printMessage("Выберите оружие (HEAVY_FLAMER, HEAVY_BOLT_GUN, GRAV_GUN): ");
        Weapon weaponType = buildWeapon(scanner);
        oldSpaceMarine.setWeaponType(weaponType);
        ioManager.printMessage("Выберите холодное оружие (CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST): ");
        MeleeWeapon meleeWeapon = buildMeleeWeapon(scanner);
        oldSpaceMarine.setMeleeWeapon(meleeWeapon);
        Chapter chapter = buildChapter(scanner);
        oldSpaceMarine.setChapter(chapter);
    }

    public Coordinates buildCoordinates(Scanner scanner) {
        ioManager.printMessage("Введите координату x (x > -483): ");
        ioManager.setUserInputInstance(scanner.nextLine());
        long x = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > -483));
        ioManager.printMessage("Введите координату y (y > -325): ");
        ioManager.setUserInputInstance(scanner.nextLine());
        int y = ioManager.validateDigit(Integer::parseInt, a -> (a != null && a > -325));
        return new Coordinates(x, y);
    }

    public Chapter buildChapter(Scanner scanner) {
        ioManager.printMessage("Введите название части: ");
        ioManager.setUserInputInstance(scanner.nextLine());
        String name = ioManager.validateString(a -> (a != null && !a.trim().isEmpty()));
        ioManager.printMessage("Теперь введи количество людей в экипаже: ");
        ioManager.setUserInputInstance(scanner.nextLine());
        long marinesCount = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > 0 && a <= 1000));
        ioManager.printMessage("Введи название мира: ");
        ioManager.setUserInputInstance(scanner.nextLine());
        String world = ioManager.validateString(a -> (!a.trim().isEmpty()));
        return new Chapter(name, marinesCount, world);
    }

    public Weapon buildWeapon(Scanner scanner) {
        List<String> weapons = Arrays.asList("HEAVY_FLAMER", "HEAVY_BOLT_GUN", "GRAV_GUN", "NONE");
        ioManager.setUserInputInstance(scanner.nextLine());
        String weaponType = ioManager.validateString(a -> weapons.contains(a.toUpperCase())).toUpperCase();
        return Weapon.getWeapon(weaponType);
    }

    public MeleeWeapon buildMeleeWeapon(Scanner scanner) {
        List<String> meleeWeapons = Arrays.asList("CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST", "NONE");
        ioManager.setUserInputInstance(scanner.nextLine());
        String meleeWeaponType = ioManager.validateString(a -> meleeWeapons.contains(a.toUpperCase())).toUpperCase();
        return MeleeWeapon.getMeleeWeapon(meleeWeaponType);
    }
}

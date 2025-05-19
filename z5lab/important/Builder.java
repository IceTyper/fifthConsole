package org.example.important;

import org.example.interfaces.IOManagable;
import org.example.models.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Класс для  построения и обновления объектов SpaceMarine.
 * Обеспечивает пошаговый ввод данных с валидацией через IOManagable.
 *
 * @author IceTyper
 * @see IOManagable
 * @see IOManager
 */
public class Builder {

    private final IOManagable ioManager;

    /**
     * Конструктор билдера с зависимостью от менеджера ввода-вывода
     *
     * @param ioManager компонент для взаимодействия с пользователем
     */
    public Builder(IOManagable ioManager) {
        this.ioManager = ioManager;
    }

    /**
     * Создание нового объекта SpaceMarine.
     * Выводит подсказки для ввода каждого поля с валидацией.
     * Работает как для ввода с консоли, так и для чтения с файла txt
     *
     * @param scanner источник ввода данных
     * @return новый объект SpaceMarine или null при прерванном вводе
     * @throws NoSuchElementException если элемент отсутствует
     */
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

    /**
     * Обновление полей существующего объекта SpaceMarine
     *
     * @param oldSpaceMarine редактируемый объект (изменяется напрямую)
     * @param scanner        источник ввода данных
     */
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

    /**
     * Создание объекта Coordinates с валидацией значений
     *
     * @param scanner источник ввода данных
     * @return объект Coordinates
     * @throws IllegalArgumentException при нарушении условий: x > -483, y > -325
     * @see Coordinates
     */
    public Coordinates buildCoordinates(Scanner scanner) {
        ioManager.printMessage("Введите координату x (x > -483): ");
        ioManager.setUserInputInstance(scanner.nextLine());
        long x = ioManager.validateDigit(Long::parseLong, a -> (a != null && a > -483));
        ioManager.printMessage("Введите координату y (y > -325): ");
        ioManager.setUserInputInstance(scanner.nextLine());
        int y = ioManager.validateDigit(Integer::parseInt, a -> (a != null && a > -325));
        return new Coordinates(x, y);
    }

    /**
     * Создание объекта Chapter с валидацией полей
     *
     * @param scanner источник ввода данных
     * @return объект Chapter
     * @throws IllegalArgumentException при нарушении условий:
     *                                  marinesCount > 0 и marinesCount <= 1000
     * @see Chapter
     */
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

    /**
     * Выбор оружия из предопределенного списка
     *
     * @param scanner источник ввода данных
     * @return выбранный тип Weapon
     * @throws IllegalArgumentException при вводе значения не из списка:
     *                                  HEAVY_FLAMER, HEAVY_BOLT_GUN, GRAV_GUN, NONE
     * @see Weapon
     */
    public Weapon buildWeapon(Scanner scanner) {
        List<String> weapons = Arrays.asList("HEAVY_FLAMER", "HEAVY_BOLT_GUN", "GRAV_GUN", "NONE");
        ioManager.setUserInputInstance(scanner.nextLine());
        String weaponType = ioManager.validateString(a -> weapons.contains(a.toUpperCase())).toUpperCase();
        return Weapon.getWeapon(weaponType);
    }

    /**
     * Выбор холодного оружия из предопределенного списка
     *
     * @param scanner источник ввода данных
     * @return выбранный тип MeleeWeapon
     * @throws IllegalArgumentException при вводе значения не из списка:
     *                                  CHAIN_SWORD, LIGHTING_CLAW, POWER_FIST, NONE
     * @see MeleeWeapon
     */
    public MeleeWeapon buildMeleeWeapon(Scanner scanner) {
        List<String> meleeWeapons = Arrays.asList("CHAIN_SWORD", "LIGHTING_CLAW", "POWER_FIST", "NONE");
        ioManager.setUserInputInstance(scanner.nextLine());
        String meleeWeaponType = ioManager.validateString(a -> meleeWeapons.contains(a.toUpperCase())).toUpperCase();
        return MeleeWeapon.getMeleeWeapon(meleeWeaponType);
    }
}
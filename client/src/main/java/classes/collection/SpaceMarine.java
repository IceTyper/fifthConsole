package classes.collection;

import java.io.Serializable;
import java.time.LocalDate;


public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {
    static private long ID = 1;
    private final Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private boolean loyal;
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    {
        this.id = ID++;
    }

    public SpaceMarine(String name, Coordinates coordinates, LocalDate creationDate, Long health, boolean loyal, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.health = health;
        this.loyal = loyal;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public Long getId() {
        return id;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public Long getHealth() {
        return health;
    }

    public void setHealth(Long health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getCreationDate() {
        return creationDate.toString();
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public boolean getLoyal() {
        return loyal;
    }

    public void setLoyal(boolean loyal) {
        this.loyal = loyal;
    }

    public long getID() {
        return ID;
    }

    public static void setID(Long id) {
        ID = id;
    }

    @Override
    public String toString() {
        return "id = " + id +
                "\n Имя = '" + name + '\'' +
                "\n Координаты = " + coordinates +
                "\n Дата создания = " + creationDate +
                "\n Здоровье = " + health +
                "\n Лояльность = " + loyal +
                "\n Оружие = " + weaponType +
                "\n Холодное оружие = " + meleeWeapon +
                "\n Часть = " + chapter;
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return this.health.compareTo(o.getHealth());
    }
}

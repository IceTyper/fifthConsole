package models;

import java.time.LocalDate;


public class SpaceMarine implements Comparable<SpaceMarine> {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private boolean loyal;
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null
    static private long ID = 1;

    {
        this.id = ID++;
    }

    public SpaceMarine(String name, Coordinates coordinates, LocalDate creationDate, Long health, boolean loyal, Weapon weaponType,  MeleeWeapon meleeWeapon, Chapter chapter) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.health = health;
        this.loyal = loyal;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public Long getId() {return id;}

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
        return Long.compare(this.id, o.id);
    }
}

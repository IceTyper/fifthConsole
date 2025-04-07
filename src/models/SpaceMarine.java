package models;

import java.time.LocalDate;

//public record SpaceMarine(Long id, String name, Coordinates coordinates, LocalDate creationDate, Long health, boolean loyal, Weapon weaponType,  MeleeWeapon meleeWeapon, Chapter chapter) {}



    /*public static boolean validateString(String name, Coordinates coordinates, LocalDate creationDate, Long health, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        if (name == null ||name.trim().isEmpty() || coordinates == null || creationDate == null) return false;
        if (health == null || health > 0) return false;
        if (weaponType == null) return false;
        if (meleeWeapon == null) return false;
        return chapter != null;
    }*/




public class SpaceMarine {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long health; //Поле не может быть null, Значение поля должно быть больше 0
    private boolean loyal;
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null


    public SpaceMarine(Long id, String name, Coordinates coordinates, LocalDate creationDate, Long health, boolean loyal, Weapon weaponType,  MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
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
}

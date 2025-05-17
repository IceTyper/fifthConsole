package client.models;

import java.io.Serializable;

public class Chapter implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000
    private String world; //Поле может быть null


    public Chapter(String name, long marinesCount, String world) {
        this.name = name;
        this.marinesCount = marinesCount;
        this.world = world;
    }


    @Override
    public String toString() {
        return "Имя = '" + name + '\'' +
                "\n Размер экипажа = " + marinesCount +
                "\n Мир = '" + world + '\'';
    }
}

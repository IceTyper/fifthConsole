package models;

public class Coordinates {
    private Long x; //Значение поля должно быть больше -483, Поле не может быть null
    private Integer y; //Значение поля должно быть больше -325, Поле не может быть null

    public Coordinates(Long x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x = " + x +
                ", y = " + y;
    }
}

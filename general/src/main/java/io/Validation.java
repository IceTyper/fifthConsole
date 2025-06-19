package io;


import java.util.function.Function;
import java.util.function.Predicate;

//статический класс для валидаторов на использование
public class Validation {
    public static <T> boolean validateString(Predicate<T> predicate, T line) {
        return predicate.test(line);
    }

    public static <T extends Number> boolean validateNumber(Predicate<T> predicate, T number) {
        return predicate.test(number);
    }

    public static <T extends Number> T getNumber(Function<String, T> function, String line) throws NumberFormatException {
        T number = function.apply(line);
        if (number == null) throw new NumberFormatException("Вы ввели совершенно не число точно");
        return number;
    }
}


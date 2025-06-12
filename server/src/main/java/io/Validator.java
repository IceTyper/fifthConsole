package io;


import java.util.function.Function;
import java.util.function.Predicate;

//статический класс для валидаторов на использование
public class Validator {
    public static boolean validateString(Predicate<String> predicate, String line) {
        return predicate.test(line);
    }

    public static <T extends Number> boolean validateDigit(Predicate<T> predicate, T number) {
        return predicate.test(number);
    }

    public static <T extends Number> T getDigit(Function<String, T> function, String line) throws NumberFormatException {
        T number = function.apply(line);
        if (number != null) {
            return number;
        }
        throw new NumberFormatException();
    }
}


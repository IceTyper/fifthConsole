package io;


import java.util.function.Function;
import java.util.function.Predicate;

//Валидирующий поля интерфейс


public interface Validator {
    static boolean validateString(Predicate<String> predicate, String line) {
        return predicate.test(line);
    }

    static <T extends Number> boolean validateDigit(Predicate<T> predicate, T number) {
        return predicate.test(number);
    }

    static <T extends Number> T getDigit(Function<String, T> function, String line) throws NumberFormatException {
        T number = function.apply(line);
        if (number != null) {
            return number;
        }
        throw new NumberFormatException();
    }
}


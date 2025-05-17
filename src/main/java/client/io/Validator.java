package client.io;


import client.exceptions.InvalidNumberException;
import client.exceptions.InvalidStringException;

import java.util.function.Function;
import java.util.function.Predicate;

//Валидирующий поля интерфейс


public interface Validator {
    static String validateString(Predicate<String> predicate, String line) throws InvalidStringException {
        if (predicate.test(line)) {
            return line;
        }
        throw new InvalidStringException();
    }

    static <T extends Number> T validateDigit(Function<String, T> function, Predicate<T> predicate, String line) throws InvalidNumberException, NumberFormatException {
        T number = getDigit(function, line);
        if (predicate.test(number)) {
            return number;
        }
        throw new InvalidNumberException();
    }

    static <T extends Number> T getDigit(Function<String, T> function, String line) throws NumberFormatException {
        T number = function.apply(line);
        if (number != null) {
            return number;
        }
        throw new NumberFormatException();
    }
}


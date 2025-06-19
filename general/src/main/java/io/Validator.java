package io;

import exceptions.InvalidNumberException;
import exceptions.InvalidStringException;

import java.util.function.Function;
import java.util.function.Predicate;

public class Validator implements Validatable {
    public static IOHandable io = new IOHandler();

    @Override
    public String validateString(Predicate<String> predicate) {
        while (true) {
            try {
                String line = io.readLine();
                if (Validation.validateString(predicate, line)) return line;
                throw new InvalidStringException("Строка не соответствует критериям, попробуйте ещё раз");
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public <T extends Number> String validateNumber(Function<String, T> function, Predicate<T> predicate) {
        while (true) {
            try {
                String line = io.readLine();
                T number = Validation.getNumber(function, line);
                if (Validation.validateNumber(predicate, number)) return line;
                throw new InvalidNumberException("попробуйте ещё раз");
            } catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Это не число, попробуйте ещё раз");
            }
        }
    }
}



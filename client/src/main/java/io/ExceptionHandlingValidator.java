package io;

import exceptions.InvalidNumberException;
import exceptions.InvalidStringException;
import exceptions.UnableToBuildElementException;

import java.util.function.Function;
import java.util.function.Predicate;

public class ExceptionHandlingValidator implements Validator {
    private ExceptionHandlingValidator() {
    }

    static String validateString(Predicate<String> predicate) throws UnableToBuildElementException {
        IO io = IOController.getInstance();
        while (true) {
            try {
                String line = io.readLine();
                return Validator.validateString(predicate, line);
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
                if (io.isReadingFromFile()) {
                    System.out.println("Если хотите продолжить ввод, введите ещё раз, иначе введите 'cancel' (без заглавных):");
                    while (true) {
                        try {
                            String line = io.readConsoleLine().trim();
                            if (!line.equals("cancel")) {
                                Validator.validateString(predicate, line);
                                break;
                            }
                            throw new UnableToBuildElementException();
                        } catch (InvalidStringException er) {
                            er.getMessage();
                        }
                    }
                }
            }
        }
    }

    static <T extends Number> T validateDigit(Function<String, T> function, Predicate<T> predicate) throws UnableToBuildElementException {
        IO io = IOController.getInstance();
        while (true) {
            try {
                String line = io.readLine();
                return Validator.validateDigit(function, predicate, line);
            } catch (NumberFormatException | InvalidNumberException e) {
                System.out.println(e.getMessage());
                if (io.isReadingFromFile()) {
                    System.out.println("Если хотите продолжить ввод, введите ещё раз, иначе введите 'cancel' (без заглавных):");
                    while (true) {
                        try {
                            String line = io.readConsoleLine().trim();
                            if (!line.equals("cancel")) {
                                Validator.validateDigit(function, predicate, line);
                                break;
                            }
                            throw new UnableToBuildElementException();
                        } catch (NumberFormatException | InvalidNumberException er) {
                            System.out.println(er.getMessage());
                        }
                    }
                }
            }
        }
    }
}



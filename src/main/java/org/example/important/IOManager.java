package org.example.important;


import org.example.Exceptions.WrongArgumentException;
import org.example.interfaces.Command;
import org.example.interfaces.IOManagable;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;


public class IOManager implements IOManagable {
    private static String userInput;

    @Override
    public void printError(String errorMessage) {
        System.err.print(errorMessage);
    }


    @Override
    public String validateString(Predicate<String> condition) {
        String input = "";
        while (input.isEmpty() || input.isBlank()) {
            try {
                if (condition.test(userInput)) {
                    input = userInput;
                } else {
                    throw new WrongArgumentException();
                }
                return input;
            } catch (WrongArgumentException e) {
                printError("Ввод выполнен неверно, попробуйте ещё раз.\n");
                Scanner scanner1 = new Scanner(System.in);
                setUserInputInstance(scanner1.nextLine());
            }
        }
        return input;
    }

    @Override
    public <T extends Number> T getDigit(Function<String, T> function) {
        T num = null;
        while (num == null) {
            try {
                num = function.apply(userInput);
            } catch (NumberFormatException | NullPointerException e) {
                printError("Неверное число у вас, введите нормально.\n");
                Scanner scanner1 = new Scanner(System.in);
                setUserInputInstance(scanner1.nextLine());
            }
        }
        return num;
    }

    @Override
    public <T extends Number> T validateDigit(Function<String, T> function, Predicate<T> condition) {
        T num = null;
        while (num == null) {
            try {
                T userInput = getDigit(function);
                if (condition.test(userInput)) {
                    num = userInput;
                } else {
                    throw new WrongArgumentException();
                }
            } catch (NumberFormatException | NullPointerException e) {
                printError("Ввод выполнен неверно, повторите.\n");
            } catch (WrongArgumentException e) {
                printError("Условия числа не выполнены, повторите\n");
                Scanner scanner1 = new Scanner(System.in);
                setUserInputInstance(scanner1.nextLine());
            }
        }
        return num;
    }

    public String getUserInputInstance() {
        return userInput;
    }

    @Override
    public void setUserInputInstance(String input) {
        userInput = input;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Command checkInputForCommand(Core core) {
        CommandManager commandManager = core.getCommandManager();
        String[] splittedInput = userInput.split(" ");
        return commandManager.getCommandsCollection().getOrDefault(splittedInput[0], null);
    }


}

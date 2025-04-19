package org.example.important;


import org.example.interfaces.Command;
import org.example.interfaces.IOManagable;

import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class IOManager implements IOManagable {
    private static String userInput;

    @Override
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String validateString(Predicate<String> condition) {
        String input = "";
        while (input.isEmpty()) {
            String userInput = getUserInput();
            if (condition.test(userInput)) {
                input = userInput;
            } else {
                printError("Ввод выполнен неверно, попробуйте ещё раз.\n");
            }
        }
        return input;
    }

    @Override
    public <T extends Number> T getDigit(Function<String, T> function) {
        T num = null;
        while (num == null) {
            try {
                num = function.apply(getUserInput());
            } catch (NumberFormatException | NullPointerException e) {
                printError("Неверное число у вас, введите нормально.\n");
            }
        }
        return num;
    }

    @Override
    public <T extends Number> T validateDigit(Function<String, T> function, Predicate<T> condition) {
        T num = null;
        while (num == null) {
            T userInput = getDigit(function);
            if (condition.test(userInput)) {
                num = userInput;
            } else {
                printError("Ввод выполнен неверно, повторите.\n");
            }
        }
        return num;
    }

    public String getUserInputInstance() {
        return userInput;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Command checkInputForCommand(Core core) {
        CommandManager commandManager = core.getCommandManager();
        while (true) {
            userInput = getUserInput().toLowerCase();
            String[] splittedInput = userInput.split(" ");
            if (commandManager.getCommandsCollection().containsKey(splittedInput[0])) {
                return commandManager.getCommandsCollection().get(splittedInput[0]);
            } else {
                core.getIOManager().printMessage("Строка не опознана, повторите попытку.\n");
            }
        }
    }

    @Override
    public void printError(String errorMessage) {
        System.err.print(errorMessage);
    }


}

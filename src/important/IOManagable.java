package important;

import commands.Command;

import java.util.function.Function;
import java.util.function.Predicate;

public interface IOManagable {

    String getUserInput();

    String validateString(Predicate<String> condition);

    void printError(String errorMessage);

    <T extends Number> T validateDigit(Function<String, T> function, Predicate<T> condition);

    <T extends Number> T getDigit(Function<String, T> function);

    void printMessage(String message);

    public String getUserInputInstance();

    Command checkInputForCommand(Core core);
}

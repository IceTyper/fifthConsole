package important;

import java.util.function.Predicate;

public interface IOManagable {

    String getUserInput();

    String validate(Predicate<String> condition);

    void printError(String errorMessage);

    <T extends Number> T validateDigit(Predicate<T> condition);

    void printMessage(String message);
}

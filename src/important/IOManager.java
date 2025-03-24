package important;

import java.util.Scanner;
import java.util.function.Predicate;

public class IOManager implements IOManagable{

    @Override
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public String validate(Predicate<String> condition) {
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
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String errorMessage) {
        System.err.print(errorMessage);
    }

    @Override
    public <T extends Number> T validateDigit(Predicate<T> condition) {
        return null;
    }
}

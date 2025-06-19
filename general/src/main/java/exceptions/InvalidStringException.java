package exceptions;

import java.io.IOException;

public class InvalidStringException extends IOException {

    public InvalidStringException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Строка введена неправильно";
    }
}

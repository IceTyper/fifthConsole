package exceptions;

import java.io.IOException;

public class InvalidNumberException extends IOException {

    public InvalidNumberException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Число не соответствует требованиям: " + super.getMessage();
    }
}

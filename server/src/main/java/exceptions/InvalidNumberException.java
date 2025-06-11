package exceptions;

import java.io.IOException;

public class InvalidNumberException extends IOException {
    @Override
    public String getMessage() {
        return "Число не соответствует требованиям: " + super.getMessage();
    }
}

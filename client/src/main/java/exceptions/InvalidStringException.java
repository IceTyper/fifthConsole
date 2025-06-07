package exceptions;

import java.io.IOException;

public class InvalidStringException extends IOException {

    @Override
    public String getMessage() {
        return "Строка введена неправильно";
    }
}

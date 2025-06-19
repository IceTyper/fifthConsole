package exceptions;

import java.io.IOException;

public class RedundantArgumentsException extends IOException {
    public RedundantArgumentsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "проблема с аргументами команды: " + super.getMessage();
    }
}

package exceptions;

import java.io.IOException;

public class RedundantArgumentsException extends IOException {

    @Override
    public String getMessage() {
        return "замечены лишние аргументы: " + super.getMessage();
    }
}

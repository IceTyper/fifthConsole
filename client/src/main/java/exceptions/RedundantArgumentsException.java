package exceptions;

import java.io.IOException;

public class RedundantArgumentsException extends IOException {
    @Override
    public String getMessage() {
        return "Замечены лишние аргументы в строке с командой";
    }
}

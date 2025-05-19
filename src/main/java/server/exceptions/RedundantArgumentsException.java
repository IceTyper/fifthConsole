package server.exceptions;

public class RedundantArgumentsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Замечены лишние аргументы в строке с командой";
    }
}

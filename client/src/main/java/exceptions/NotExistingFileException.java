package exceptions;

public class NotExistingFileException extends RuntimeException {
    public String getMessage() {
        return "File Not Found";
    }
}

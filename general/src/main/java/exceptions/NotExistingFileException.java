package exceptions;

public class NotExistingFileException extends RuntimeException {

    public NotExistingFileException(String message) {
        super(message);
    }

    public String getMessage() {
        return "File Not Found";
    }
}

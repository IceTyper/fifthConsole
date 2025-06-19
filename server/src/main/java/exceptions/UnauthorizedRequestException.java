package exceptions;

public class UnauthorizedRequestException extends RuntimeException {
    public UnauthorizedRequestException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Запрос от неавторизованного пользователя: " + super.getMessage();
    }
}

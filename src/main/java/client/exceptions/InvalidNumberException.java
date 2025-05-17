package client.exceptions;

import java.io.IOException;

public class InvalidNumberException extends IOException {
    @Override
    public String getMessage() {
        return "Число введено неверно";
    }
}

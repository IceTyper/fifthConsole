package org.example.Exceptions;

public class WrongCommandCastException extends RuntimeException {
    public String getMessage() {
        return "Вы пытаетесь использовать команду в неприемлемом месте";
    }
}

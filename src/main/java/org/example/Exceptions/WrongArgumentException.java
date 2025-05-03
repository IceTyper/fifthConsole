package org.example.Exceptions;

public class WrongArgumentException extends RuntimeException {
    public String getMessage() {
        return "Аргумент введён неправильно";
    }
}

package org.example.Exceptions;

public class RedundantArguments extends RuntimeException {
    public RedundantArguments(String message) {
        super(message);
    }
}

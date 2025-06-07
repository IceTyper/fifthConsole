package classes.exceptions;

public class RecursionDangerException extends RuntimeException {
    public String getMessage() {
        return "Программа была на грани вступления во тьму рекурсии";
    }
}

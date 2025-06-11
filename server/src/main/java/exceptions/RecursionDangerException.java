package exceptions;

public class RecursionDangerException extends RuntimeException {
    public String getMessage() {
        return "Программа была на грани вступления во тьму рекурсии.\nБыли запущены следующие файлы: " + super.getMessage();
    }
}

package classes.exceptions;

public class UnableToBuildElementException extends RuntimeException {
  @Override
  public String getMessage() {
    return "процесс постройки элемента коллекции был прерван";
  }
}

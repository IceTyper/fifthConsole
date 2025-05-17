package client.exceptions;

public class UnableToBuildElementException extends RuntimeException {
  @Override
  public String getMessage() {
    return super.getMessage() + "процесс постройки элемента коллекции был прерван";
  }
}

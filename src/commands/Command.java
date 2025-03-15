package commands;

public interface Command {
    String getDescription();
    void execute();
}

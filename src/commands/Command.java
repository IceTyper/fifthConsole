package commands;
import important.Core;

public interface Command {
    String getDescription();
    void execute(Core core, String[] args);
}

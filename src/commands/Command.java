package commands;
import Managers.Core;

public interface Command {
    String getDescription();
    void execute(Core core);
}

package client.commands.utility;

import client.commands.Command;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Stream;

public class CommandHandler {
    private static CommandHandler instance;
    private final HashMap<String, Command> commands = new HashMap<>();

    private CommandHandler() {
    }

    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    private void addCommand(Command command) {
        commands.put(command.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(), command);
    }

    public void addCommands(Command... commands) {
        Stream.of(commands).forEach(this::addCommand);
    }

    public boolean checkIfContains(String command) {
        return commands.containsKey(command);
    }

    public void executeCommand(String[] args) {
        commands.get(args[0]).execute(Stream.of(args).skip(1).toArray(String[]::new));
    }

    public Collection<Command> getValues() {
        return commands.values();
    }
}

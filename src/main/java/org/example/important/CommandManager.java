package org.example.important;

import org.example.interfaces.Command;

import java.util.HashMap;

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    public void addCommand(Command command) {
        commands.put(command.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase(), command);
    }

    public void addCommands(Command... commands) {
        for (Command command : commands) {
            addCommand(command);
        }
    }

    public HashMap<String, Command> getCommandsCollection() {
        return commands;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    @Override
    public String toString() {
        return "CommandManager{" +
                "org.example.org.example.commands=" + commands +
                '}';
    }
}

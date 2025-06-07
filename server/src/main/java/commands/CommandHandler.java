package commands;

import connectionchamber.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandHandler {
    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);
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

    public String executeCommand(Message msg) {
        if (msg == null) {
            logger.info("msg is null.");
            return null;
        }
        if (msg.args() != null) {
            LinkedList<Object> fields = Arrays.stream(msg.args()).collect(Collectors.toCollection(LinkedList::new));
            commands.get(msg.commandName()).setQueue(fields);
        }
        return commands.get(msg.commandName()).execute();
    }

    public Collection<Command> getValues() {
        return commands.values();
    }
}

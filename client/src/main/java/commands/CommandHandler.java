package commands;

import connectionchamber.Message;
import exceptions.InvalidStringException;
import exceptions.RedundantArgumentsException;
import io.Handler;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class CommandHandler {
    private static final AbstractList<String> commands = new ArrayList<>();

    private CommandHandler() {
    }

    private static void addCommand(String command) {
        commands.add(command.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase());
    }

    public static void addCommands(String... commands) {
        Stream.
                of(commands).
                forEach(CommandHandler::addCommand);
    }

    public static boolean checkIfContains(String command) {
        return commands.contains(command);
    }

    public static Message executeCommand(String[] args) throws RedundantArgumentsException, InvalidStringException {
        if (args.length == 0 || !checkIfContains(args[0])) {
            throw new InvalidStringException();
        }
        boolean ifTwoArgsNeeded = Arrays.asList("update", "execute_script", "filter_greater_than_melee_weapon", "remove_by_id").contains(args[0]);
        if (ifTwoArgsNeeded && args.length != 2) {
            throw new InvalidStringException();
        }
        if (args[0].equals("execute_script")) {
            Command executeScript = new ExecuteScript();
            executeScript.execute(args);
            return null;
        }
        if (args[0].equals("exit")) {
            Command exit = new Exit();
            exit.execute(args);
            return null;
        }
        boolean ifAdd = Arrays.asList("add", "add_if_max", "remove_lower").contains(args[0]);
        if (args.length > 1) {
            throw new RedundantArgumentsException();
        }
        if (ifAdd) {
            Message msg = new Message(args[0], new Handler().recordSpacemarineFields());
            return msg;
        }
        if (ifTwoArgsNeeded) {
            return new Message(args[0], new String[]{args[1]});
        }
        return new Message(args[0], null);
    }

    public static Collection<String> getValues() {
        return commands.stream().toList();
    }

    public static String getCommand(String command) {
        return commands.get(commands.indexOf(command));
    }
}

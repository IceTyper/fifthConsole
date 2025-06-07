package commands;

import collection.MeleeWeapon;
import connectionchamber.Message;
import exceptions.InvalidStringException;
import exceptions.RedundantArgumentsException;
import io.Handler;

import java.io.IOException;
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
        commands.add(transformCommand(command));
    }

    public static void addCommands(String... commands) {
        Stream.
                of(commands).
                forEach(CommandHandler::addCommand);
    }

    public static boolean checkIfContains(String command) {
        return commands.contains(transformCommand(command));
    }

    private static String transformCommand(String command) {
        return command.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    public static Message executeCommand(String[] args) throws RedundantArgumentsException, InvalidStringException {
        boolean ifTwoArgsNeeded = Arrays.asList("update", "execute_script", "filter_greater_than_melee_weapon", "remove_by_id").contains(args[0]);
        if (args.length == 0 || !checkIfContains(args[0]) || (ifTwoArgsNeeded && args.length < 2)) {
            throw new InvalidStringException();
        }
        String command = transformCommand(args[0]);
        ArrayList<String> simpleCmds = new ArrayList<>(Arrays.asList("help", "info", "show", "clear", "remove_head", "sum_of_health", "print_field_ascending_health"));
        ArrayList<String> addCmds = new ArrayList<>(Arrays.asList("add", "add_if_max", "remove_lower"));
        if (simpleCmds.contains(command) && args.length == 1)
            return new Message(command, null);

        if (addCmds.contains(command) && args.length == 1)
            return new Message(command, new Handler().recordSpacemarineFields());

        if (command.equals("execute_script")) {
            new ExecuteScript().execute(args); return null;}

        if (command.equals("exit") && args.length == 1) {
            new Exit().execute(args); return null;}

        if (command.equals("filter_greater_than_melee_weapon") && args.length == 2) {
            if (MeleeWeapon.getMeleeWeapon(args[1]) != null)
                return new Message(command, new Object[]{MeleeWeapon.getMeleeWeapon(args[1].toUpperCase())});
            throw new InvalidStringException();
        }

        if (command.equals("remove_by_id") && args.length == 2) {
            try {
                long id = Long.parseLong(args[1]);
                return new Message(command, new Object[]{id});
            } catch (NumberFormatException e) {
                throw new InvalidStringException();
            }
        }

        if (command.equals("update") && args.length == 2) {
            try {
                long id = Long.parseLong(args[1]);
                return new UpdateHandler().execute(id);
            } catch (NumberFormatException e) {
                throw new InvalidStringException();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        throw new RedundantArgumentsException();
    }

    public static Collection<String> getValues() {
        return commands.stream().toList();
    }

    public static String getCommand(String command) {
        return commands.get(commands.indexOf(command));
    }
}

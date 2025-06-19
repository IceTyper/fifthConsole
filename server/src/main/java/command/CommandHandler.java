package command;

import connection.Message;
import connection.User;
import connectionchamber.Registration;
import exceptions.InvalidStringException;
import exceptions.RedundantArgumentsException;
import exceptions.UnauthorizedRequestException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.stream.Stream;

public class CommandHandler {
    private static CommandHandler instance;
    private final HashMap<String, AbstractCommand> commands = new HashMap<>();

    private CommandHandler() {
    }

    public static CommandHandler getInstance() {
        if (instance == null) {
            instance = new CommandHandler();
        }
        return instance;
    }

    private void addCommand(AbstractCommand command) {
        String commandName = command.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
        commands.put(commandName, command);
    }

    public boolean checkCommand(String commandName) {
        return commands.containsKey(commandName);
    }

    public void addCommands(AbstractCommand[] commands) {
        Stream.of(commands).forEach(this::addCommand);
    }

    public Object[] executeCommand(Message msg) {
        try {
            if ((msg.args().length == 0 || !(msg.args()[0] instanceof User user) || (!Registration.ifUserExists(user))) && !msg.args()[1].equals("5325c93c9e4697ff4395b23b9077123d"))
                throw new UnauthorizedRequestException("простите, но вы не авторизованы (либо данные по пути к нам потерялись)");
            String command = msg.commandName();
            if (!commands.containsKey(command) ||
                    (command.equals("register_user") &&  !(msg.args().length > 1 && msg.args()[1].equals("5325c93c9e4697ff4395b23b9077123d"))))
                throw new InvalidStringException("неправильно введена команда: " + command.toLowerCase());
            if (!commands.get(command).checkArgNumber(msg.args().length))
                throw new RedundantArgumentsException("количество аргументов не соответствует требуемому командой " + command.toLowerCase());

            Object[] result = commands.get(command).execute(msg.args());

            if (!result[0].equals("RecordFields") && !result[0].equals("PrintMessage")) {
                return Stream.concat(Stream.of("PrintMessage"), Arrays.stream(result)).toArray();
            }
            return result;
        } catch (Exception e) {
            return new Object[]{"PrintMessage", e.getMessage()};
        }
    }

    public Collection<AbstractCommand> getValues() {
        return commands.values();
    }
}

package commands;

import connection.Message;

import java.rmi.ConnectIOException;
import java.util.HashMap;

public class CommandHandler {
    private static final HashMap<String, Command> commands = new HashMap<>();

    static {
        commands.put("PrintMessage", new PrintMessage());
        commands.put("RecordFields", new RecordFields());
    }

    public static void executeCommand(Message msg) throws ConnectIOException {
        if (msg.args().length == 0 || !(msg.args()[0] instanceof String) || !commands.containsKey((String) msg.args()[0]))
            throw new ConnectIOException("что-то случилось с данными");
        commands.get((String) msg.args()[0]).execute(msg);
    }
}

package commands;

import connection.Message;

import java.rmi.ConnectIOException;
import java.util.Arrays;

public class PrintMessage extends Command {

    @Override
    public void execute(Message msg) {
        String border = "------------------------------------------------------------------------";
        System.out.println(border);
        System.out.println("Результат выполнения команды " + msg.commandName() + ":");
        if (Arrays.stream(msg.args()).skip(1).allMatch(a -> a instanceof Message)) {
            Arrays.stream(msg.args()).skip(1).forEach(a -> {
                try {
                    CommandHandler.executeCommand((Message) a);
                } catch (ConnectIOException e) {
                    System.out.println(e.getMessage());
                }
            });
        } else {
            Arrays.stream(msg.args(), 1, msg.args().length).forEach(System.out::println);
        }
        System.out.println(border);
    }
}

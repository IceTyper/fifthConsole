package commands;

import connection.Message;
import connectionchamber.Sender;
import connectionchamber.UDPDatagramClient;
import io.FieldsHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class RecordFields extends Command {
    @Override
    public void execute(Message msg) {
        Object[] fields = FieldsHandler.recordSpacemarineFields();
        ArrayList<Object> args = new ArrayList<>();
        args.add(UDPDatagramClient.getUser());
        if (msg.args().length > 1) args.addAll(Arrays.stream(msg.args()).skip(1).toList());
        args.addAll(Arrays.asList(fields));
        try {
            Message message = Sender.sendAndReceive(new Message(msg.commandName(), args.toArray()));
            CommandHandler.executeCommand(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package commands;

import connection.Message;
import connectionchamber.Sender;
import io.Handler;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class UpdateHandler {

    public Message execute(long id) throws IOException, ClassNotFoundException {
        Sender sender = new Sender();
        Message msg = sender.sendAndReceive(new Message("update", new Object[]{id}));
        boolean ifElementExists = (boolean) msg.args()[0];
        if (!ifElementExists) {
            System.out.println("Элемента с таким ID не существует");
            return null;
        }
        Object[] fields = new Handler().recordSpacemarineFields();
        Object[] args = Stream.concat(Stream.of(id), Arrays.stream(fields)).toArray();
        return new Message("update", args);
    }
}

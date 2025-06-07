package commands;

import connectionchamber.Message;
import connectionchamber.Sender;
import io.Handler;

import java.io.IOException;

public class UpdateHandler {

    public Message execute(long id) throws IOException, ClassNotFoundException {
        Sender sender = new Sender();
        Message msg = sender.sendAndReceive(new Message("update", new Object[]{id}));
        boolean ifElementExists = (boolean) msg.args()[0];
        if (!ifElementExists) {
            System.out.println("Элемента с таким ID не существует");
            return null;
        }
        return new Message("update", new Object[]{id, new Handler().recordSpacemarineFields()});
    }
}

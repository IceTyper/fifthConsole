package utility;

import commands.Command;
import connectionchamber.ServerConnectable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MessageSender implements MessageSendable {
    @Override
    public Command acceptMessage(ServerConnectable server, String[] args) {
        try (ByteArrayInputStream byteIn = new ByteArrayInputStream(server.receive());
             ObjectInputStream in = new ObjectInputStream(byteIn);) {
            Command command = (Command) in.readObject();
            return command;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void sendMessage(ServerConnectable server, String[] args) {

    }
}
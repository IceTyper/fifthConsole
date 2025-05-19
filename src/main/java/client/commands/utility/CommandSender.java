package client.commands.utility;

import client.commands.Command;
import client.connectionchamber.ClientConnectable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class CommandSender implements CommandSendable {

    @Override
    public void sendCommandToServer(Command command, ClientConnectable client) {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                ObjectOutputStream out =  new ObjectOutputStream(byteOut)) {
            out.writeObject(command);
            byte[] data = byteOut.toByteArray();
            client.send(data);
            byte[] receivedData = client.receive();
            System.out.println(Arrays.toString(receivedData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

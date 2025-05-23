package utility;

import commands.Command;
import connectionchamber.ClientConnectable;
import connectionchamber.Message;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class CommandSender implements CommandSendable {

    @Override
    public void sendCommandToServer(Message msg, ClientConnectable client) {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                ObjectOutputStream out =  new ObjectOutputStream(byteOut)) {
            out.writeObject(msg);
            byte[] data = byteOut.toByteArray();
            client.send(data);
            byte[] receivedData = client.receive();
            System.out.println(Arrays.toString(receivedData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

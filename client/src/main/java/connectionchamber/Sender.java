package connectionchamber;

import commands.CommandHandler;

import java.io.IOException;

public class Sender {
    private static ClientConnectable client;

    public ClientConnectable getClient() {
        return client;
    }
    public void setClient(ClientConnectable client) {
        Sender.client = client;
    }

    public void send(String[] line) throws IOException, ClassNotFoundException {
        Message msg = CommandHandler.executeCommand(line);
        if (msg != null) {
            Serializator serializator = new Serializator();
            byte[] byteMsg = serializator.serialize(msg);
            client.send(byteMsg);
            byte[] byteReceivedMsg = client.receive();
            Message receivedMsg = (Message) serializator.deserialize(byteReceivedMsg);
            System.out.println("Ответ от сервера: " + receivedMsg.getMessage());
        }
    }
}


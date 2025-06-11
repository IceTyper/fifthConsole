package connectionchamber;

import connection.Serializator;
import connection.Message;

import java.io.IOException;

public class Sender {
    private static ClientConnectable client;

    public ClientConnectable getClient() {
        return client;
    }

    public void setClient(ClientConnectable client) {
        Sender.client = client;
    }

    public Message sendAndReceive(Message msg) throws IOException, ClassNotFoundException {
        Serializator serializator = new Serializator();
        byte[] byteMsg = serializator.serialize(msg);
        client.send(byteMsg);
        byte[] byteReceivedMsg = client.receive();
        return (Message) serializator.deserialize(byteReceivedMsg);
    }
}

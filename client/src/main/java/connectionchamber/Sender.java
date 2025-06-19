package connectionchamber;

import connection.Serializator;
import connection.Message;

import java.io.IOException;

public class Sender {
    private static Connectable client;

    public static Connectable getClient() {
        return client;
    }

    public static void setClient(Connectable client) {
        Sender.client = client;
    }

    public static Message sendAndReceive(Message msg) throws IOException, ClassNotFoundException {
        byte[] byteMsg = Serializator.serialize(msg);
        client.send(byteMsg);
        byte[] byteReceivedMsg = client.receive();
        return (Message) Serializator.deserialize(byteReceivedMsg);
    }
}

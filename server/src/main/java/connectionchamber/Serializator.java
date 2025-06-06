package connectionchamber;

import java.io.*;

public class Serializator {

    public byte[] serialize(Serializable message) throws IOException {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteOut);) {
            out.writeObject(message);
            return byteOut.toByteArray();
        }
    }

    public Message deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteIn = new ByteArrayInputStream(buffer);
        ObjectInputStream in = new ObjectInputStream(byteIn)) {
            Object message = in.readObject();
            return (Message) message;
        }
    }
}

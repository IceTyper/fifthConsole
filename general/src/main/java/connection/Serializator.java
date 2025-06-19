package connection;

import java.io.*;

public class Serializator {

    public static byte[] serialize(Serializable message) throws IOException {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteOut);) {
            out.writeObject(message);
            return byteOut.toByteArray();
        }
    }

    public static Serializable deserialize(byte[] buffer) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteIn = new ByteArrayInputStream(buffer);
             ObjectInputStream in = new ObjectInputStream(byteIn)) {
            return (Serializable) in.readObject();
        }
    }
}

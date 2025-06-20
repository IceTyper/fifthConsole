package connectionchamber;

import java.io.IOException;

public interface Connectable {
    void start() throws IOException;

    void send(byte[] data) throws IOException;

    byte[] receive() throws IOException;

    static void close() throws IOException {}
}

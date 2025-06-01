package connectionchamber;

import java.io.IOException;

public interface ServerConnectable {
    void start() throws IOException;

    void send(byte[] data) throws IOException;

    byte[] receive() throws IOException;

    void close() throws IOException;
}

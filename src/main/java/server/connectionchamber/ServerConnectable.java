package server.connectionchamber;

import java.io.IOException;
import java.net.InetAddress;

public interface ServerConnectable {
    void start(int port) throws IOException;

    void send(byte[] data, InetAddress inetAddress, int port) throws IOException;

    byte[] receive() throws IOException;

    void close() throws IOException;
}

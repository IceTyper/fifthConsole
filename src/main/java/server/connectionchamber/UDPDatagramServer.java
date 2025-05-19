package server.connectionchamber;

import java.io.IOException;
import java.net.DatagramSocket;

public class UDPDatagramServer implements ServerConnectable {
    private DatagramSocket socket;


    @Override
    public void start(int port) throws IOException {

    }

    @Override
    public void send(byte[] data) throws IOException {

    }

    @Override
    public byte[] receive() throws IOException {
        return new byte[0];
    }

    @Override
    public void close() throws IOException {

    }
}

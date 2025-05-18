package client.connectionchamber;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class TCPChannelClient implements ClientConnectable {
    private SocketChannel socket;


    @Override
    public void connect(String host, int port) throws IOException {

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

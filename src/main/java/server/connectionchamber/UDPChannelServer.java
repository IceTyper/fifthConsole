package server.connectionchamber;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class UDPChannelServer implements ServerConnectable {
    private DatagramChannel channel;


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

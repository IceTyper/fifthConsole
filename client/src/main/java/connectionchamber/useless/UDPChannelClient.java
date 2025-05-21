package connectionchamber.useless;

import connectionchamber.ClientConnectable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPChannelClient implements ClientConnectable {
    private DatagramChannel channel;
    private InetSocketAddress address;

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

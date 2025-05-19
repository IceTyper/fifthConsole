package connectionchamber;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDatagramClient implements ClientConnectable {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;


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

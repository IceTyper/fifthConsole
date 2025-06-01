package connectionchamber;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPDatagramClient implements ClientConnectable {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public UDPDatagramClient() throws SocketException {
        socket = new DatagramSocket();
    }

    @Override
    public void connect(String host, int port) throws IOException {
        address = InetAddress.getByName(host);
        this.port = port;
    }

    @Override
    public void send(byte[] data) throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        socket.send(packet);
    }

    @Override
    public byte[] receive() {
        byte[] buffer = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        return buffer;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}

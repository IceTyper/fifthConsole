package classes.connectionchamber;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPDatagramClient implements ClientConnectable {
    private DatagramSocket socket = new DatagramSocket();
    private InetAddress address;
    private int port = 9898;

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
        System.out.println("Отправляем запрос серверу...");
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        socket.send(packet);
    }

    @Override
    public byte[] receive() throws IOException {
        System.out.println("Ждём ответ от сервера...");
        byte[] buffer = new byte[4096];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        int length = packet.getLength();
        byte[] received = new byte[length];
        System.arraycopy(buffer, 0, received, 0, length);
        System.out.println("Ответ получен!");
        return received;
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}

package connectionchamber;

import connection.User;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPDatagramClient implements Connectable {
    private static User user;
    private final DatagramSocket socket;
    private InetAddress address;
    private int port;

    public UDPDatagramClient() throws SocketException {
        socket = new DatagramSocket();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UDPDatagramClient.user = user;
    }

    @Override
    public void connect(String host, int port) throws IOException {
        address = InetAddress.getByName(host);
        this.port = port;
    }

    @Override
    public void send(byte[] data) throws IOException {
        //System.out.println("Отправляем запрос серверу...");
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        socket.send(packet);
    }

    @Override
    public byte[] receive() throws IOException {
        //System.out.println("Ждём ответ от сервера...");
        try {
            socket.setSoTimeout(30 * 1000);
            byte[] buffer = new byte[4096];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            System.out.println("Ждём ответ от сервера...");
            socket.receive(packet);
            int length = packet.getLength();
            byte[] received = new byte[length];
            System.arraycopy(buffer, 0, received, 0, length);
            //System.out.println("Ответ получен!");
            return received;
        } catch (IOException e) {
            throw new IOException("превышено время ожидания от сервера (30 секунд)");
        }
        finally {
            socket.setSoTimeout(0);
        }
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}

package connectionchamber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPChannelServer implements Connectable {
    public static int PORT;
    private static DatagramChannel channel;

    static {
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int clientPort;
    private String clientAddress;

    public int getClientPort() {
        return clientPort;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    @Override
    public void start() throws IOException {
        if (!channel.socket().isBound()) channel.bind(new InetSocketAddress(PORT));
    }

    @Override
    public void send(byte[] data) throws IOException {
        UDPChannel.send(data, channel, clientAddress, clientPort);
    }

    @Override
    public byte[] receive() throws IOException {
        var packet = UDPChannel.receive(channel, 4096);
        if (packet == null || packet.client() == null) return null;
        clientAddress = packet.client().getAddress().getHostAddress();
        clientPort = packet.client().getPort();
        return packet.data();
    }

    @Override
    public void close() throws IOException {
        channel.close();
    }
}

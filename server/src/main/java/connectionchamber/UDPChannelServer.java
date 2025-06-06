package connectionchamber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPChannelServer implements ServerConnectable {
    public static int PORT = 9057;
    private static DatagramChannel channel;

    static {
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int clientPort;
    private String clientAddress;


    @Override
    public void start() throws IOException {
        channel.bind(new InetSocketAddress(PORT));

    }

    @Override
    public void send(byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.flip();
        channel.send(buffer, new InetSocketAddress(clientAddress, clientPort));

    }

    @Override
    public byte[] receive() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        buffer.clear();
        InetSocketAddress client = (InetSocketAddress) channel.receive(buffer);
        if (client == null) return null;
        clientAddress = client.getAddress().getHostAddress();
        clientPort = client.getPort();
        buffer.flip();
        int length = buffer.remaining();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }

    @Override
    public void close() throws IOException {
        channel.close();
    }
}

package connectionchamber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPChannelServer implements ServerConnectable {
    private static DatagramChannel channel;
    public static int PORT = 9057;

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


        //Selector selector = Selector.open();
        //SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
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
        SocketAddress client = channel.receive(buffer);
        if (client == null) return null;
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

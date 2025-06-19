package connectionchamber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

//статический класс, нужный для классов, исполняющих
//отправку и принятие сообщений с помощью UDP и каналов
public class UDPChannel {

    public static void send(byte[] data, DatagramChannel channel, String address, int port) throws IOException {
        channel.send(ByteBuffer.wrap(data), new InetSocketAddress(address, port));
    }

    public static ReceivedPacket receive(DatagramChannel channel, int bufferSize) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        InetSocketAddress client = (InetSocketAddress) channel.receive(buffer);
        if (client == null) return null;
        buffer.flip();
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);
        return new ReceivedPacket(data, client);
    }

    public record ReceivedPacket(byte[] data, InetSocketAddress client) {}
}

package connectionchamber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

//статический класс, нужный для классов, исполняющих
//отправку и принятие сообщений с помощью UDP и каналов
public class UDPChannel {

    public static void send(byte[] data, DatagramChannel channel, String clientAddress, int clientPort) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        channel.send(buffer, new InetSocketAddress(clientAddress, clientPort));
    }

    public static byte[] receive(DatagramChannel channel, int bufferSize) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        buffer.clear();
        InetSocketAddress client = (InetSocketAddress) channel.receive(buffer);
        if (client == null) return null;
        buffer.flip();
        int length = buffer.remaining();
        byte[] bytes = new byte[length];
        buffer.get(bytes);
        return bytes;
    }
}

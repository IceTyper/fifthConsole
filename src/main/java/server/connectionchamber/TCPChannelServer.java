package server.connectionchamber;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TCPChannelServer implements ServerConnectable {
    private ServerSocketChannel serverSocket;
    private SocketChannel socket;

    @Override
    public void start(int port) throws IOException {
        serverSocket = ServerSocketChannel.open();
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

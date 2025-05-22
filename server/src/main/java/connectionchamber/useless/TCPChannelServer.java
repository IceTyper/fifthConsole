package connectionchamber.useless;

import connectionchamber.ServerConnectable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TCPChannelServer implements ServerConnectable {
    private static ServerSocketChannel serverSocket;

    static {
        /*
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(Server.PORT));
        serverSocket.configureBlocking(false);
        */
    }

    private SocketChannel socket;

    @Override
    public void start(int port) throws IOException {


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

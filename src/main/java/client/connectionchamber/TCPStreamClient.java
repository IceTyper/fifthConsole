package client.connectionchamber;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPStreamClient implements ClientConnectable {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    @Override
    public void connect(String host, int port) throws IOException {
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

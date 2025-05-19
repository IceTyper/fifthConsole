package connectionchamber;

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
        socket = new Socket(host, port);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }

    @Override
    public void send(byte[] data) throws IOException {
        outputStream.write(data);
    }


    @Override
    public byte[] receive() throws IOException {
        return inputStream.readAllBytes();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}

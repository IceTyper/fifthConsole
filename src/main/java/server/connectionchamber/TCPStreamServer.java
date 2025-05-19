package server.connectionchamber;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPStreamServer implements ServerConnectable {
    private static ServerSocket serverSocket;
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;


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

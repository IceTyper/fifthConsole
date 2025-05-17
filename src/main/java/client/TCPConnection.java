package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPConnection {
    private Socket socket;

    public TCPConnection(int port) throws IOException {
        socket = new Socket("localhost", port);
    }

    public void closeClient(Socket socket) throws IOException {
        socket.close();
    }

    public void writeToServer(String message) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
    }
}

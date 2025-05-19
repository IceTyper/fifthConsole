import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnection {
    private static ServerSocket serverSocket;

    public TCPConnection(int port) throws IOException {
        if (serverSocket == null) {
            serverSocket = new ServerSocket(port);
        }
    }

    public Socket connectAndGetClient() throws IOException {
        return serverSocket.accept();
    }

    public void closeServer() throws IOException {
        serverSocket.close();
    }

    public String acceptClientInput() throws IOException {
        return "";
    }
}

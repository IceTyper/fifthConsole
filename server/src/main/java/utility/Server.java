package utility;

import connectionchamber.ServerConnectable;
import connectionchamber.TCPChannelServer;

public class Server {
    public static final int PORT = 9057;


    public static void main(String[] args) {

        ServerConnectable server = new TCPChannelServer();

    }
}

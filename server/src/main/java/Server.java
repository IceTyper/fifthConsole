import connectionchamber.Message;
import connectionchamber.Serializator;
import connectionchamber.ServerConnectable;
import connectionchamber.UDPChannelServer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Server {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isOn = true;

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        System.out.print("Port: ");
        UDPChannelServer.PORT = Integer.parseInt(scanner.nextLine());
        ServerConnectable server = new UDPChannelServer();
        server.start();
        System.out.println("Server is running");
        Serializator serializator = new Serializator();

        while (isOn) {
            while (true) {
                byte[] receivedMsg = server.receive();
                if (receivedMsg == null) {
                    Thread.sleep(3);
                    continue;
                }
                System.out.println(Arrays.toString(receivedMsg));
                Message msg = (Message) serializator.deserialize(receivedMsg);
                System.out.println(msg);
            }
        }
    }


}

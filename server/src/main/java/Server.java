import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        try {
            TCPConnection server = new TCPConnection(9057);
            Socket client = server.connectAndGetClient();
            Scanner in = new Scanner(client.getInputStream());
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

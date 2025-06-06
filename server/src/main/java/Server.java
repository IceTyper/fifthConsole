import commands.Add;
import commands.CommandHandler;
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
        init();
        server.start();
        System.out.println("Server is running");
        Serializator serializator = new Serializator();
        CommandHandler command = CommandHandler.getInstance();

        while (isOn) {

            byte[] receivedMsg = server.receive();
            if (receivedMsg == null) {
                Thread.sleep(3);
                continue;
            }

            Message msg = serializator.deserialize(receivedMsg);
            System.out.println("received:" + msg);
            String answer = command.executeCommand(msg);
            Message answerMsg = new Message(msg.commandName(), new String[]{answer});
            byte[] byteMsg = serializator.serialize(answerMsg);
            System.out.println("sending: " + answerMsg + "\n in bytes: " + Arrays.toString(byteMsg));
            server.send(byteMsg);

        }
    }

    //"Help", "Add", "Exit", "AddIfMax", "Clear", "ExecuteScript",
    //                "FilterGreaterThanMeleeWeapon", "Info", "RemoveById", "RemoveHead", "RemoveLower",
    //                "Show", "SumOfHealth", "Update"
    private static void init() {
        CommandHandler.getInstance().addCommands(new Add());
    }

}

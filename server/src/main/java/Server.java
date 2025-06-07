import commands.Add;
import commands.CommandHandler;
import commands.Exit;
import connectionchamber.Message;
import connectionchamber.Serializator;
import connectionchamber.UDPChannelServer;

import java.io.IOException;
import java.util.Scanner;

public class Server {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        System.out.print("Port: ");
        UDPChannelServer.PORT = Integer.parseInt(scanner.nextLine());
        UDPChannelServer server = new UDPChannelServer();
        init();
        server.start();
        System.out.println("Server is running");
        Serializator serializator = new Serializator();
        CommandHandler command = CommandHandler.getInstance();

        while (Exit.isOn()) {
            if (System.in.available() > 0) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("exit") || input.equals("save")) {
                    String result = command.executeCommand(new Message(input, null));
                    System.out.println(result);
                }
            }

            byte[] receivedMsg = server.receive();
            if (receivedMsg == null) {
                Thread.sleep(3);
                continue;
            }
            Message msg = serializator.deserialize(receivedMsg);
            System.out.println("received: " + msg);
            System.out.println("from client: " + server.getClientAddress() + ":" + server.getClientPort());
            String answer = command.executeCommand(msg);
            Message answerMsg = new Message(msg.commandName(), new String[]{answer});
            byte[] byteMsg = serializator.serialize(answerMsg);
            System.out.println("sending: " + answerMsg);
            server.send(byteMsg);
        }
    }


    //"Help", "Add", "Exit", "AddIfMax", "Clear", "ExecuteScript",
    //                "FilterGreaterThanMeleeWeapon", "Info", "RemoveById", "RemoveHead", "RemoveLower",
    //                "Show", "SumOfHealth", "Update"
    private static void init() {
        CommandHandler.getInstance().addCommands(new Add(), new Exit());
    }
}

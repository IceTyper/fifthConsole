import commands.*;
import connectionchamber.Message;
import connectionchamber.Serializator;
import connectionchamber.UDPChannelServer;
import io.FileHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Server {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        while (true) {
            try {
                System.out.print("Port: ");
                UDPChannelServer.PORT = Integer.parseInt(scanner.nextLine());
                if (UDPChannelServer.PORT < 1000 || UDPChannelServer.PORT > 9999) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number. It must be an integer like 'xxxx'.");
            }
        }
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
                    Object[] result = command.executeCommand(new Message(input, null));
                    System.out.println(Arrays.toString(result));
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
            Object[] answer = command.executeCommand(msg);
            Message answerMsg = new Message(msg.commandName(), answer);
            byte[] byteMsg = serializator.serialize(answerMsg);
            //System.out.println("sending: " + answerMsg);
            server.send(byteMsg);
        }
    }


    private static void init() {
        CommandHandler.getInstance().addCommands(new Add(), new Exit(), new Save(), new Show(),
                new Help(), new Info(), new PrintFieldAscendingHealth(), new Clear(),
                new RemoveHead(), new SumOfHealth(), new FilterGreaterThanMeleeWeapon(),
                new RemoveLower(), new AddIfMax(), new RemoveById(), new Update());
        System.out.println(new FileHandler().readFromFile());
    }
}

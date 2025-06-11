import command.*;
import command.commands.*;
import connection.Message;
import connection.Serializator;
import connectionchamber.UDPChannelServer;
import io.FileHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Server {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        try {
            if (args.length == 0) {
                throw new IOException("Port number is required as a single argument.");
            }
            if (args.length > 1) {
                throw new IOException("Too many arguments. Only one argument is expected: the port number.");
            }
            int port = Integer.parseInt(args[0]);
            if (port < 1000 || port > 9999) {
                throw new IOException("Port number must be between 1000 and 9999.");
            }
            UDPChannelServer.PORT = port;
        } catch (NumberFormatException | IOException e) {
            System.out.println(e.getMessage() + "Please provide a valid port number: 1000-9999.");
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
            Message msg = (Message) serializator.deserialize(receivedMsg);
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

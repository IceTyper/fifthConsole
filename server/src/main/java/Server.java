import command.AbstractCommand;
import command.CommandHandler;
import command.ExecuteScript;
import command.commands.*;
import connection.Message;
import connection.Serializator;
import connectionchamber.UDPChannelServer;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        checkPort(args);
        init();
        System.out.println("Server is running");
        CommandHandler command = CommandHandler.getInstance();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        new Thread(() -> {
            while (Exit.isOn()) {
                try {
                    UDPChannelServer server = new UDPChannelServer();
                    server.start();
                    byte[] receivedMsg = server.receive();

                    cachedThreadPool.execute(() -> {
                        try {
                            Message msg = (Message) Serializator.deserialize(receivedMsg);
                            System.out.println("received: " + msg);
                            System.out.println("from client: " + server.getClientAddress() + ":" + server.getClientPort());
                            Object[] answer = command.executeCommand(msg);

                            fixedThreadPool.execute(() -> {
                                try {
                                    Message answerMsg = new Message(msg.commandName(), answer);
                                    byte[] byteMsg = Serializator.serialize(answerMsg);
                                    System.out.println("sending: " + answerMsg);
                                    server.send(byteMsg);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                        } catch (IOException e) {
                            System.out.println("Error in server thread: " + e.getMessage());
                        } catch (ClassNotFoundException e) {
                            System.out.println("Error deserializing message: " + e.getMessage());
                        }
                    });


                } catch (IOException e) {
                    System.out.println("Error in server thread: " + e.getMessage());
                }
            }
        }).start();

        while (Exit.isOn()) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("exit")) new Exit(a(0)).execute(null);
            else if (input.equals("save")) new Save(a(0)).execute(null);
            else System.out.println("Unknown command");
        }
    }


    private static int[] a(int... nums) {
        return nums;
    }

    private static void init() {
        CommandHandler.getInstance().addCommands(new AbstractCommand[]{new Add(a(1, 11)), new Show(a(1)),
                new Help(a(1)), new Info(a(1)), new PrintFieldAscendingHealth(a(1)), new Clear(a(1)),
                new RemoveHead(a(1)), new SumOfHealth(a(1)), new FilterGreaterThanMeleeWeapon(a(2)),
                new RemoveLower(a(1, 11)), new AddIfMax(a(1, 11)), new RemoveById(a(2)),
                new Update(a(2, 12)), new RegisterUser(a(3)), new ExecuteScript(a(2))});
    }

    private static void checkPort(String[] args) {
        try {
            if (args.length != 1) {
                throw new IOException("Port number is required as a single argument.");
            }
            int port = Integer.parseInt(args[0]);
            if (port < 1000 || port > 9999) {
                throw new IOException("Port number must be between 1000 and 9999.");
            }
            UDPChannelServer.PORT = port;
        } catch (NumberFormatException | IOException e) {
            System.out.println(e.getMessage() + "Please provide a valid port number: 1000-9999.");
            System.exit(1);
        }
    }
}

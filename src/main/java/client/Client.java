package client;

import client.commands.Add;
import client.commands.Exit;
import client.exceptions.InvalidStringException;
import client.io.IO;
import client.io.IOController;


public class Client {
    private static boolean isRunningClient = true;

    public static void endClient() {
        isRunningClient = false;
    }

    public static void main(String[] args) {
        try {
            TCPConnection client = new TCPConnection(9057);
            client.writeToServer("Test test yeaaaah");
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
        runLoop();
    }

    private static void runLoop() {
        System.out.println("Приложение запущено и готово к работе");
        IO io = IOController.getInstance();
        CommandHandler commandHandler = CommandHandler.getInstance();
        while (isRunningClient) {
            try {
                System.out.println("Введите команду");
                String[] line = io.readConsoleLine().split(" ");
                if (!commandHandler.checkIfContains(line[0])) {
                    throw new InvalidStringException();
                }
                commandHandler.executeCommand(line);
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void init() {
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.addCommands(new Add(), new Exit());
    }
}


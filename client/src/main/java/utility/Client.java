package utility;

import commands.Add;
import commands.Command;
import commands.Exit;
import commands.Help;
import connectionchamber.ClientConnectable;
import connectionchamber.TCPStreamClient;
import exceptions.InvalidStringException;
import exceptions.RedundantArgumentsException;
import io.IO;
import io.IOController;

import java.io.IOException;


public class Client {
    private static boolean isRunningClient = true;

    public static void endClient() {
        isRunningClient = false;
    }

    public static void main(String[] args) {
        init();
        ClientConnectable client = connectWithServer("localhost", 5757);
        runLoop(client);
    }

    private static void runLoop(ClientConnectable client) {
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
                Command command = commandHandler.getCommand(line[0]);
                CommandSendable sender = new CommandSender();
                if (!(command instanceof Help)) {
                    sender.sendCommandToServer(command, client);
                }
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            } catch (RedundantArgumentsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void init() {
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.addCommands(new Add(), new Exit(), new Help());
    }

    public static ClientConnectable connectWithServer(String host, int port) {
        ClientConnectable client = new TCPStreamClient();
        try {
            client.connect(host, port);
            System.out.println("Коннект с сервером установлен");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }
}




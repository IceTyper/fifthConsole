package utility;

import commands.CommandHandler;
import commands.Exit;
import connectionchamber.ClientConnectable;
import connectionchamber.Message;
import connectionchamber.Serializator;
import connectionchamber.UDPDatagramClient;
import exceptions.InvalidStringException;
import exceptions.RedundantArgumentsException;
import io.IO;
import io.IOController;

import java.io.IOException;


public class Client {
    private static ClientConnectable client;

    public static ClientConnectable getClient() {
        return client;
    }

    public static void main(String[] args) {
        try {
            client = connectWithServer("localhost");
            init();
            runLoop();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Не удалось поймать коннект с сервером");
        }
    }

    private static void runLoop() {
        System.out.println("Приложение запущено и готово к работе");
        IO io = IOController.getInstance();
        while (Exit.isRunningClient()) {
            try {
                System.out.println("Введите команду");
                String[] line = io.readConsoleLine().split(" ");
                Message msg = CommandHandler.executeCommand(line);
                if (msg != null) {
                    Serializator serializator = new Serializator();
                    byte[] byteMsg = serializator.serialize(msg);
                    client.send(byteMsg);
                    byte[] byteReceivedMsg = client.receive();
                    Message receivedMsg = (Message) serializator.deserialize(byteReceivedMsg);
                    System.out.println("received: " + receivedMsg);
                }
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            } catch (RedundantArgumentsException e) {
                System.out.println(e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void init() {
        CommandHandler.addCommands("Help", "Add", "Exit", "AddIfMax", "Clear", "ExecuteScript",
                "FilterGreaterThanMeleeWeapon", "Info", "RemoveById", "RemoveHead", "RemoveLower",
                "Show", "SumOfHealth", "Update");
    }

    public static ClientConnectable connectWithServer(String host) throws IOException {
        System.out.println("Введите порт сервера, к которому хотите подключиться: ");
        IOController io = IOController.getInstance();
        int port = Integer.parseInt(io.readLine());
        ClientConnectable client = new UDPDatagramClient();
        client.connect(host, port);
        return client;
    }
}


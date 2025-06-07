import commands.CommandHandler;
import commands.Exit;
import connectionchamber.ClientConnectable;
import connectionchamber.Message;
import connectionchamber.Sender;
import connectionchamber.UDPDatagramClient;
import exceptions.InvalidStringException;
import exceptions.RedundantArgumentsException;
import io.IO;
import io.IOController;

import java.io.IOException;


public class Client {

    public static void main(String[] args) {
        init();
        runLoop();
    }

    private static void runLoop() {
        ClientConnectable client = connectWithServer("localhost");
        Sender sender = new Sender();
        sender.setClient(client);
        System.out.println("Приложение запущено и готово к работе");
        IO io = IOController.getInstance();
        while (Exit.isRunningClient()) {
            try {
                System.out.println("Введите команду");
                String[] line = io.readConsoleLine().split(" ");
                Message msg = CommandHandler.executeCommand(line);
                if (msg != null) {
                    Message receivedMsg = sender.sendAndReceive(msg);
                    System.out.println("Ответ от сервера: " + receivedMsg.getMessage());
                }
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            } catch (RedundantArgumentsException e) {
                System.out.println(e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getClass().getName());
            }
        }
    }

    private static void init() {
        CommandHandler.addCommands("Help", "Add", "Exit", "AddIfMax", "Clear", "ExecuteScript",
                "FilterGreaterThanMeleeWeapon", "Info", "RemoveById", "RemoveHead", "RemoveLower",
                "Show", "SumOfHealth", "Update");
    }

    public static ClientConnectable connectWithServer(String host) {
        System.out.println("Введите порт сервера, к которому хотите подключиться: ");
        IOController io = IOController.getInstance();
        while (true) {
            try {
                int port = Integer.parseInt(io.readLine());
                ClientConnectable client = new UDPDatagramClient();
                client.connect(host, port);
                return client;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат порта. Пожалуйста, введите целое четырёхзначное число");
            } catch (IOException e) {
                System.out.println("Не удалось подключиться к серверу: " + e.getMessage());
            }
        }
    }
}
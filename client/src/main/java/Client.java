import commands.CommandHandler;
import commands.Exit;
import connectionchamber.ClientConnectable;
import connectionchamber.Message;
import connectionchamber.UDPDatagramClient;
import exceptions.InvalidStringException;
import exceptions.RedundantArgumentsException;
import io.IO;
import io.IOController;


public class Client {

    public static void main(String[] args) {
        init();
        //ClientConnectable client = connectWithServer("localhost", 5757);
        runLoop(new UDPDatagramClient());
    }

    private static void runLoop(ClientConnectable client) {
        System.out.println("Приложение запущено и готово к работе");
        IO io = IOController.getInstance();
        while (Exit.isRunningClient()) {
            try {
                System.out.println("Введите команду");
                String[] line = io.readConsoleLine().split(" ");
                Message msg = CommandHandler.executeCommand(line);
                System.out.println("мэссадж пойман");
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            } catch (RedundantArgumentsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void init() {
        CommandHandler.addCommands("Help", "Add", "Exit", "AddIfMax", "Clear", "ExecuteScript",
                "FilterGreaterThanMeleeWeapon", "Info", "RemoveById", "RemoveHead", "RemoveLower",
                "Show", "SumOfHealth", "Update");
    }

    public static ClientConnectable connectWithServer(String host, int port) {
        return new UDPDatagramClient();
    }
}




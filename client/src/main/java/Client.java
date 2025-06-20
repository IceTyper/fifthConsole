import commands.CommandHandler;
import connection.Message;
import connection.User;
import connectionchamber.Connectable;
import connectionchamber.HashMD5;
import connectionchamber.Sender;
import connectionchamber.UDPDatagramClient;
import exceptions.InvalidNumberException;
import io.IOHandable;
import io.IOHandler;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;


public class Client {

    public static void main(String[] args) {
        int port = validatePort(args);
        runLoop(port);
    }

    private static int validatePort(String[] args) {
        try {
            if (args.length != 1)
                throw new InvalidNumberException("Укажите порт (четырёхзначное число) одним аргументом.");
            int port = Integer.parseInt(args[0]);
            if (port < 1000 || port > 9999) {
                throw new InvalidNumberException("В порту должно быть 4 цифры.");
            }
            return port;
        } catch (NumberFormatException | InvalidNumberException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return 0;
    }

    private static void runLoop(int port) {
        Connectable client = connectWithServer("localhost", port);
        Sender.setClient(client);
        while (UDPDatagramClient.getUser() == null) {
            registerUser();
        }
        System.out.println("Приложение запущено и готово к работе");
        IOHandable io = new IOHandler();

        while (true) {
            try {
                System.out.print("Введите команду: ");
                String[] line = io.readConsoleLine().split(" ");
                if (Objects.equals(line[0], "exit") && line.length == 1) {
                    System.out.println("Прощай, юзер!");
                    client.close();
                    System.exit(0);
                }
                Object[] args = Stream.concat(
                                Stream.of(UDPDatagramClient.getUser()),
                                Arrays.stream(line).skip(1))
                        .toArray();
                Message msg = new Message(line[0], args);
                Message receivedMsg = Sender.sendAndReceive(msg);
                CommandHandler.executeCommand(receivedMsg);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        /*while (Exit.isRunningClient()) {
            try {
                System.out.println("Введите команду");
                String[] line = io.readConsoleLine().split(" ");
                Message msg = CommandHandler.executeCommand(line);
                if (msg != null) {
                    Message receivedMsg = sender.sendAndReceive(msg);
                    System.out.println("Ответ от сервера: ");
                    Arrays.stream(receivedMsg.args()).map(String.class::cast).forEach(System.out::println);
                }
            } catch (InvalidStringException e) {
                System.out.println(e.getMessage());
            } catch (RedundantArgumentsException e) {
                System.out.println(e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }*/
    }

    /*private static void init() {
        CommandHandler.addCommands("Help", "Add", "Exit", "AddIfMax", "Clear", "ExecuteScript",
                "FilterGreaterThanMeleeWeapon", "Info", "RemoveById", "RemoveHead", "RemoveLower",
                "Show", "SumOfHealth", "Update", "PrintFieldAscendingHealth");
    }*/

    public static Connectable connectWithServer(String host, int port) {
        try {
            Connectable client = new UDPDatagramClient();
            client.connect(host, port);
            return client;
        } catch (IOException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
        return null;
    }

    private static String[] request(IOHandable io, String line) {
        System.out.print("Введите логин " + line + ": ");
        String login = io.readConsoleLine();
        System.out.print("Введите пароль " + line + ": ");
        String password;
        Console console = System.console();
        if (console != null) {
            char[] passwordChars = console.readPassword();
            password = new String(passwordChars);
            java.util.Arrays.fill(passwordChars, ' ');
        } else {
            password = io.readConsoleLine();
        }
        return new String[]{login, password};
    }

    private static void registerUser() {
        IOHandable io = new IOHandler();
        System.out.println("Добро пожаловать! Чтобы зарегистрироваться, введите 's', чтобы войти - 'l'");
        while (UDPDatagramClient.getUser() == null) {
            String input = io.readConsoleLine();
            try {
                if ("s".equalsIgnoreCase(input)) {
                    String[] lines = request(io, "для регистрации");
                    User u = new User(lines[0], HashMD5.hash(lines[1]));
                    Message msg = Sender.sendAndReceive(new Message("register_user", new Object[]{u, "5325c93c9e4697ff4395b23b9077123d", "register"}));
                    boolean isRegistered = (boolean) msg.args()[1];
                    if (!isRegistered) {
                        throw new Exception("Пользователь с таким именем уже существует");
                    } else {
                        UDPDatagramClient.setUser(u);
                        System.out.println("Регистрация прошла успешно!");
                    }
                } else if ("l".equalsIgnoreCase(input)) {
                    String[] lines = request(io, "для входа");
                    User u = new User(lines[0], HashMD5.hash(lines[1]));
                    Message msg = Sender.sendAndReceive(new Message("register_user", new Object[]{u, "5325c93c9e4697ff4395b23b9077123d", "login"}));
                    boolean isLoggedIn = (boolean) msg.args()[1];
                    if (!isLoggedIn) {
                        throw new Exception("Неверный логин или пароль");
                    }
                    UDPDatagramClient.setUser(u);
                    System.out.println("Вы успешно вошли в систему!");
                } else {
                    throw new Exception("Неверная команда. Введите 's' для регистрации или 'l' для входа.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
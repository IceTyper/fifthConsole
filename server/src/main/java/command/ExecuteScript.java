package command;


import connection.Message;
import connection.User;
import exceptions.InvalidStringException;
import exceptions.RecursionDangerException;
import io.IOHandable;
import io.IOHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Команда для выполнения скрипта из указанного файла.
 * Поддерживает проверку на рекурсивный вызов и обработку ошибок ввода.
 * Файл скрипта должен находиться в директории src/main/resources/ и иметь расширение .txt.
 * При обнаружении рекурсии или ошибок в аргументах выбрасывается RecursionDangerException.
 *
 * @author IceTyper
 */
public class ExecuteScript extends AbstractCommand implements Helpable {

    private static ArrayList<String> commands = new ArrayList<>();

    public ExecuteScript(int[] ints) {
        super(ints);
    }


    @Override
    public String getDescription() {
        return "execute_script {file_name} - считывание и исполнение " +
                "скрипта из указанного файла. В скрипте содержатся " +
                "команды в таком же виде, в котором их вводит " +
                "пользователь в интерактивном режиме.";
    }

    @Override
    public Object[] execute(Object[] args) {
        String fileName = (String) args[1];
        try {
            User user = (User) args[0];
            if (commands.contains(fileName)) {
                throw new RecursionDangerException();
            }
            if (!fileName.endsWith(".txt")) {
                throw new InvalidStringException("Файл должен иметь расширение .txt");
            }
            commands.add(fileName);
            ArrayList<Message> messages = new ArrayList<>();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(fileName).toFile()));
            IOHandable io = new IOHandler();
            io.selectScanner(new Scanner(bufferedReader));
            String nextLine;
            while (io.hasNextLine()) {
                nextLine = io.readLine().trim();
                if (!(nextLine.isEmpty())) {
                    Object[] msg = Stream.concat(Stream.of(user), Arrays.stream(nextLine.split(" ")).skip(1)).toArray();
                    String commandName = nextLine.split(" ")[0];
                    Message request = new Message(commandName, msg);
                    Object[] result = CommandHandler.getInstance().executeCommand(request);
                    String command = (String) result[0];
                    if (command.equals("RecordFields")) {
                        ArrayList<Object> fields = new ArrayList<>();
                        for (int i = 0; i < 10; i++) {
                            if (io.hasNextLine()) {
                                fields.add(io.readLine().trim());
                            }
                        }
                        if (fields.size() < 10) {
                            messages.add(new Message(commandName, new Object[]{"PrintMessage", "Недостаточно полей для создания элемента"}));
                        } else {
                            fields.addAll(0, Arrays.stream(result).skip(1).toList());
                            fields.add(0, user);
                            result = CommandHandler.getInstance().executeCommand(new Message(commandName, fields.toArray()));
                            messages.add(new Message(commandName, result));
                        }
                    } else {
                        messages.add(new Message(commandName, result));
                    }
                }
            }
            commands.remove(fileName);
            io.selectConsoleScanner();
            bufferedReader.close();
            return messages.toArray();
        } catch (Exception e) {
            commands.remove(fileName);
            return new Object[]{e.getMessage()};
        } finally {
            commands.clear();
        }
        /*try {

                    Object[] obj = CommandHandler.executeCommand(new String[]{nextLine});
                    Message receivedMsg = sender.sendAndReceive(msg);
                    if (receivedMsg != null) {
                        System.out.println("ответ от сервера: ");
                        Arrays.stream(receivedMsg.args()).map(String.class::cast).forEach(System.out::println);
                    }
                }
            }

        } catch (NotExistingFileException | RecursionDangerException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException | IOException | ClassNotFoundException e) {
            System.out.println("execute script error" + e.getClass().getName());
            e.printStackTrace();
        } */
    }
}

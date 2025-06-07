package commands;


import connectionchamber.Message;
import connectionchamber.Serializator;
import exceptions.NotExistingFileException;
import exceptions.RecursionDangerException;
import io.IO;
import io.IOController;
import utility.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Команда для выполнения скрипта из указанного файла.
 * Поддерживает проверку на рекурсивный вызов и обработку ошибок ввода.
 * Файл скрипта должен находиться в директории src/main/resources/ и иметь расширение .txt.
 * При обнаружении рекурсии или ошибок в аргументах выбрасывается RecursionDangerException.
 *
 * @author IceTyper
 */
public class ExecuteScript extends Command {

    /**
     * Список путей к выполняемым скриптам для предотвращения рекурсии.
     * Хранит строки, то есть названия файлов, что помогает предотвратить цикл.
     */
    private static ArrayList<String> commands = new ArrayList<>();

    /**
     * Возвращает описание команды для отображения в справке.
     *
     * @return Строка с описанием функционала команды.
     */
    @Override
    public String getDescription() {
        return "execute_script {file_name} - считывание и исполнение " +
                "скрипта из указанного файла. В скрипте содержатся " +
                "команды в таком же виде, в котором их вводит " +
                "пользователь в интерактивном режиме.";
    }

    /**
     * Выполняет скрипт из указанного файла.
     * Проверяет аргументы, существование файла, рекурсию и последовательно выполняет команды.
     * В случае ошибок валидации или чтения файла выводит сообщения в консоль.
     *
     * @param args Аргументы команды, где args[1] - имя файла скрипта
     */
    @Override
    public void execute(String[] args) {
        try {
            if (!args[1].endsWith(".txt")) {
                throw new NotExistingFileException();
            }
            if (commands.contains(args[1])) {
                throw new RecursionDangerException();
            }
            commands.add(args[1]);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Paths.get(args[1]).toFile()));
            IO io = IOController.getInstance();
            io.selectScanner(new Scanner(bufferedReader));
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                if (!(nextLine.isEmpty())) {
                    Message msg = CommandHandler.executeCommand(new String[]{nextLine});
                    Serializator serializator = new Serializator();
                    byte[] byteMsg = serializator.serialize(msg);
                    Client.getClient().send(byteMsg);
                    byte[] byteReceivedMsg = Client.getClient().receive();
                    Message receivedMsg = (Message) serializator.deserialize(byteReceivedMsg);
                    System.out.println("received: " + receivedMsg);
                }
            }
            commands.remove(args[1]);
            io.selectConsoleScanner();
            bufferedReader.close();
        } catch (NotExistingFileException | RecursionDangerException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException | IOException | ClassNotFoundException e) {
            System.out.println("execute script error" + e.getClass().getName());
            e.printStackTrace();
        } finally {
            commands.clear();
        }
    }
}

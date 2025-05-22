package commands;


import connectionchamber.Message;
import exceptions.NotExistingFileException;
import exceptions.RecursionDangerException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

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
            BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]));
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null) {
                if (nextLine.isEmpty()) {
                    continue;
                }
                Message msg = CommandHandler.executeCommand(nextLine.split(" "));
                System.out.println("msg: " + msg);
            }
            commands.remove(args[1]);
            bufferedReader.close();
        } catch (NotExistingFileException | RecursionDangerException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException | IOException e) {
            e.printStackTrace();
        }
    }
}

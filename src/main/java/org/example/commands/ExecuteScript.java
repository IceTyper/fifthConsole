package org.example.commands;

import org.example.Exceptions.NotExistingFileException;
import org.example.Exceptions.RecursionDangerException;
import org.example.Exceptions.RedundantArgumentsException;
import org.example.Exceptions.WrongArgumentException;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.interfaces.IOManagable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Команда для выполнения скрипта из указанного файла.
 * Поддерживает проверку на рекурсивный вызов и обработку ошибок ввода.
 * Файл скрипта должен находиться в директории src/main/resources/ и иметь расширение .txt.
 * При обнаружении рекурсии или ошибок в аргументах выбрасывается RecursionDangerException.
 * @author IceTyper
 */
public class ExecuteScript implements Command {

    /**
     * Список путей к выполняемым скриптам для предотвращения рекурсии.
     * Хранит строки, то есть названия файлов, что помогает предотвратить цикл.
     */
    private static ArrayList<String> commands = new ArrayList<>();

    /**
     * Возвращает описание команды для отображения в справке.
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
     * @param core экземпляр класса Core, используемый для доступа к менеджеру ввода-вывода и другим командам
     * @param scanner Объект сканера для чтения ввода (не используется в текущей реализации)
     * @param args Аргументы команды, где args[1] - имя файла скрипта
     * @throws RedundantArgumentsException Если переданы избыточные аргументы
     * @throws NotExistingFileException Если файл не существует или имеет неверное расширение
     * @throws RecursionDangerException При обнаружении рекурсивного вызова
     * @throws WrongArgumentException Если команда в скрипте введена некорректно
     * @see org.example.important.Core
     */
    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 2) {
                throw new RedundantArgumentsException();
            }
            if (args.length == 1 || !args[1].endsWith(".txt")) {
                throw new NotExistingFileException();
            }
            if (commands.contains(args[1])) {
                throw new RecursionDangerException();
            }
            commands.add(args[1]);
            Scanner bufferedReader = new Scanner(new BufferedReader(new FileReader("src/main/resources/" + args[1])));
            String nextLine;
            while (bufferedReader.hasNextLine()) {
                nextLine = bufferedReader.nextLine();
                if (nextLine == null || nextLine.isEmpty()) {
                    continue;
                }
                IOManagable ioManager = core.getIOManager();
                ioManager.setUserInputInstance(nextLine);
                Command command = ioManager.checkInputForCommand(core);
                if (command == null) {
                    throw new WrongArgumentException();
                } else {
                    command.execute(core, bufferedReader, nextLine.split(" "));
                }
            }
            commands.remove(args[1]);
            bufferedReader.close();
        } catch (RedundantArgumentsException | IOException | NotExistingFileException | RecursionDangerException e) {
            System.out.println(e.getMessage());
        } catch (WrongArgumentException e) {
            System.out.println("Команда введена неверно");
        } catch (NoSuchElementException e) {
        }
    }
}

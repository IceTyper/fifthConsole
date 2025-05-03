package org.example.commands;

import org.example.Exceptions.NotExistingFileException;
import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.interfaces.IOManagable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ExecuteScript implements Command {
    private BufferedReader bufferedReader = null;

    @Override
    public String getDescription() {
        return "execute_script {file_name} - считывание и исполнение " +
                "скрипта из указанного файла. В скрипте содержатся " +
                "команды в таком же виде, в котором их вводит " +
                "пользователь в интерактивном режиме.";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try (BufferedReader bReader = new BufferedReader(new FileReader("src/main/resources/" + args[1]))) {
            if (args.length > 2) {
                throw new RedundantArgumentsException();
            }
            if (args.length == 1 || !args[1].endsWith(".txt")) {
                throw new NotExistingFileException();
            }
            bufferedReader = bReader;
            while (bufferedReader.ready()) {
                String nextLine = bufferedReader.readLine();
                if (nextLine == null || nextLine.isEmpty()) {
                    continue;
                }
                IOManagable ioManager = core.getIOManager();
                ioManager.setUserInputInstance(nextLine);
                Command command = ioManager.checkInputForCommand(core);
                if (command == null) {
                    System.out.println("Команда " + nextLine + "введена некорректно");
                } else {
                    command.execute(core, new Scanner(bufferedReader), nextLine.split(" "));
                }
            }
        } catch (RedundantArgumentsException | IOException | NotExistingFileException e) {
            System.out.println(e.getMessage());
        }

    }
}

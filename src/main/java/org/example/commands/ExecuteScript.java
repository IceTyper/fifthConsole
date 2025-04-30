package org.example.commands;

import org.example.Exceptions.RedundantArguments;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.interfaces.IOManagable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteScript implements Command {
    @Override
    public String getDescription() {
        return "execute_script {file_name} - считывание и исполнение " +
                "скрипта из указанного файла. В скрипте содержатся " +
                "команды в таком же виде, в котором их вводит " +
                "пользователь в интерактивном режиме.";
    }

    @Override
    public void execute(Core core, String[] args) {
        if (args.length > 2) {
            throw new RedundantArguments();
        } else if (args.length == 1) {
            System.out.println("Имя файла не указано");
        } else if (!args[1].endsWith(".txt")) {
            System.out.println("Файл должен иметь расширение txt");
        } else {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/" + args[1]))) {
                while (bufferedReader.ready()) {
                    String nextLine = bufferedReader.readLine();
                    IOManagable ioManager = core.getIOManager();
                    ioManager.setUserInputInstance(nextLine);
                    Command command = ioManager.checkInputForCommand(core);
                    if (command == null) {
                        System.out.println("Команда " + nextLine + "введена некорректно");
                    } else {
                        command.execute(core, nextLine.split(" "));
                    }

                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
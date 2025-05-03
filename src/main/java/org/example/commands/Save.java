package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Save implements Command {
    @Override
    public String getDescription() {
        return "save {file_name} - сохранение коллекции в файл (имя файла вводить необязательно)";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 2) {
                throw new RedundantArgumentsException();
            }
            if (args.length == 2 && args[1].endsWith(".json")) {
                core.getFileManager().saveToFile(new File("C:/Users/fmusa/IdeaProjects/fifthConsole/src/main/resources/" + args[1]), core.getCollectionManager());
            } else {
                core.getFileManager().saveToFile(new File("C:/Users/fmusa/IdeaProjects/fifthConsole/src/main/resources/collection.json"), core.getCollectionManager());
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

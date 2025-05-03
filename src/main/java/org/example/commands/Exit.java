package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.util.Scanner;

public class Exit implements Command {
    @Override
    public String getDescription() {
        return "exit - завершение программы без сохранения в файл";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
        core.endCore();
        System.out.println("Прощай, юзер!");
    }
}

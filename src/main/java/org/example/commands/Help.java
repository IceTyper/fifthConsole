package org.example.commands;

import org.example.Exceptions.RedundantArgumentsException;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.util.Scanner;


public class Help implements Command {

    @Override
    public String getDescription() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public void execute(Core core, Scanner scanner, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            for (Command command : core.getCommandManager().getCommandsCollection().values()) {
                System.out.println(command.getDescription());
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

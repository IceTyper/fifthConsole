package org.example.commands;

import org.example.important.Core;
import org.example.interfaces.Command;


public class Help implements Command {

    @Override
    public String getDescription() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public void execute(Core core, String[] args) {
        for (Command command : core.getCommandManager().getCommandsCollection().values()) {
            System.out.println(command.getDescription());
        }
    }
}

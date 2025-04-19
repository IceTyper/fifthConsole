package org.example.commands;

import org.example.Exceptions.RedundantArguments;
import org.example.important.Core;
import org.example.interfaces.Command;


public class Help implements Command {

    @Override
    public String getDescription() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public void execute(Core core, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArguments();
            }
            for (Command command : core.getCommandManager().getCommandsCollection().values()) {
                System.out.println(command.getDescription());
            }
        } catch (RedundantArguments e) {
            System.out.println(e.printProblem(args));
        }
    }
}

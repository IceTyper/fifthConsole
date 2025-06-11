package command.commands;

import command.AbstractCommand;
import command.CommandHandler;

import java.util.ArrayList;

public class Help extends AbstractCommand {
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        for (AbstractCommand command : CommandHandler.getInstance().getValues()) {
            response.add(command.getDescription());
        }
        return response.toArray();
    }
}

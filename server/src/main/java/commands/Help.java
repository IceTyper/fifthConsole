package commands;

import java.util.ArrayList;

public class Help extends Command {
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public Object[] execute() {
        ArrayList<Object> response = new ArrayList<>();
        for (Command command : CommandHandler.getInstance().getValues()) {
            response.add(command.getDescription());
        }
        return response.toArray();
    }
}

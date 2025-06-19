package command.commands;

import command.AbstractCommand;
import command.CommandHandler;
import command.Helpable;

import java.util.ArrayList;

public class Help extends AbstractCommand implements Helpable {
    public Help(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        for (AbstractCommand command : CommandHandler.getInstance().getValues()) {
            if (command instanceof Helpable) {
                response.add(((Helpable) command).getDescription());
            }
        }
        return response.toArray();
    }
}


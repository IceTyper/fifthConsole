package client.commands;


import client.commands.utility.CommandHandler;
import client.exceptions.RedundantArgumentsException;


public class Help extends Command {

    @Override
    public String getDescription() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            for (Command command : CommandHandler.getInstance().getValues()) {
                System.out.println(command.getDescription());
            }
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

package commands;


import exceptions.RedundantArgumentsException;
import utility.CommandHandler;


public class Help extends Command {

    @Override
    public String getDescription() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public void execute(String[] args) throws RedundantArgumentsException {
        if (args.length > 1) {
            throw new RedundantArgumentsException();
        }
        for (Command command : CommandHandler.getInstance().getValues()) {
            System.out.println(command.getDescription());
        }
    }
}

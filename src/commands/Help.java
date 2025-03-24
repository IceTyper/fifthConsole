package commands;

import important.Core;

public class Help implements Command{

    @Override
    public String getDescription() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public void execute(Core core) {
        for (Command command : core.getCommandManager().getCommands().values()) {
            System.out.println(command.getDescription());
        }
    }
}

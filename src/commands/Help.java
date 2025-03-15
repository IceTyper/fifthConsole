package commands;

public class Help implements Command{

    @Override
    public String getDescription() {
        return "help - вывод справки по доступным командам";
    }

    @Override
    public void execute() {

    }
}

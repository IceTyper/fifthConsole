package commands;

public class Exit implements Command {
    @Override
    public String getDescription() {
        return "exit - завершение команды без сохранения в файл";
    }

    @Override
    public void execute() {
        System.out.println("exit");
    }
}

package commands;

public class Save implements Command {
    @Override
    public String getDescription() {
        return "save - сохранение коллекции в файл";
    }

    @Override
    public void execute() {
        System.out.println("Save");
    }
}

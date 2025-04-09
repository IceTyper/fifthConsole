package commands;
import important.Core;
import interfaces.Command;

public class Save implements Command {
    @Override
    public String getDescription() {
        return "save - сохранение коллекции в файл";
    }

    @Override
    public void execute(Core core, String[] args) {
        System.out.println("Save");
    }
}

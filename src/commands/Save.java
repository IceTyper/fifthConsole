package commands;
import Managers.Core;
public class Save implements Command {
    @Override
    public String getDescription() {
        return "save - сохранение коллекции в файл";
    }

    @Override
    public void execute(Core core) {
        System.out.println("Save");
    }
}

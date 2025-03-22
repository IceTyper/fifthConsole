package commands;

import Managers.Core;

public class Info implements Command {
    @Override
    public String getDescription() {
        return "info - вывод информации о коллекции";
    }

    @Override
    public void execute(Core core) {
        System.out.println("Info");
    }
}

package commands;

import Managers.Core;

public class Exit implements Command {
    @Override
    public String getDescription() {
        return "exit - завершение команды без сохранения в файл";
    }

    @Override
    public void execute(Core core) {
        System.out.println("exit");
    }
}

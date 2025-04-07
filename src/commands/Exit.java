package commands;

import important.Core;

public class Exit implements Command {
    @Override
    public String getDescription() {
        return "exit - завершение команды без сохранения в файл";
    }

    @Override
    public void execute(Core core, String[] args) {
        System.out.println("exit");
    }
}

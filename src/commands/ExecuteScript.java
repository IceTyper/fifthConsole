package commands;

import important.Core;

public class ExecuteScript implements Command {
    @Override
    public String getDescription() {
        return "execute_script {file_name} - считывание и исполнение " +
                "скрипта из указанного файла. В скрипте содержатся " +
                "команды в таком же виде, в котором их вводит " +
                "пользователь в интерактивном режиме.";
    }

    @Override
    public void execute(Core core) {
        System.out.println("Execute script");
    }
}

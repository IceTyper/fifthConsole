package commands;

import exceptions.RedundantArgumentsException;
import utility.Client;

public class Exit extends Command {
    @Override
    public String getDescription() {
        return "exit - завершение программы без сохранения в файл";
    }

    @Override
    public void execute(String[] args) throws RedundantArgumentsException {
        if (args.length > 1) {
            throw new RedundantArgumentsException();
        }
        Client.endClient();
        System.out.println("Прощай, юзер!");
    }
}

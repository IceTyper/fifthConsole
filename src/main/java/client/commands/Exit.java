package client.commands;


import client.Client;
import client.exceptions.RedundantArgumentsException;

public class Exit extends Command {
    @Override
    public String getDescription() {
        return "exit - завершение программы без сохранения в файл";
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            Client.endClient();
            System.out.println("Прощай, юзер!");
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

package classes.commands;

import classes.exceptions.RedundantArgumentsException;


public class Exit extends Command {
    private static boolean isRunningClient = true;

    public static boolean isRunningClient() {
        return isRunningClient;
    }

    @Override
    public String getDescription() {
        return "exit - завершение программы без сохранения в файл";
    }

    @Override
    public void execute(String[] args) throws RedundantArgumentsException {
        if (args.length > 1) {
            throw new RedundantArgumentsException();
        }
        isRunningClient = false;
        System.out.println("Прощай, юзер!");
    }
}

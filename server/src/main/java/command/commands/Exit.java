package command.commands;

import command.AbstractCommand;

import java.util.Arrays;

public class Exit extends AbstractCommand {
    private static boolean isOn = true;

    public static boolean isOn() {
        return isOn;
    }

    @Override
    public String getDescription() {
        return "exit - Завершение программы";
    }

    @Override
    public Object[] execute(Object[] args) {
        System.out.println(Arrays.toString(new Save().execute(args)));
        isOn = false;
        return new Object[]{"Shutting down the server..."};
    }
}

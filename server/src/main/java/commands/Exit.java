package commands;

import java.util.Arrays;

public class Exit extends Command {
    private static boolean isOn = true;

    public static boolean isOn() {
        return isOn;
    }

    @Override
    public String getDescription() {
        return "exit - Завершение программы";
    }

    @Override
    public Object[] execute() {
        System.out.println(Arrays.toString(new Save().execute()));
        isOn = false;
        return new Object[]{"Shutting down the server..."};
    }
}

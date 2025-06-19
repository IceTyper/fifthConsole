package command.commands;

import command.AbstractCommand;
import database.DataBase;

import java.util.Arrays;

public class Exit extends AbstractCommand {
    private static boolean isOn = true;

    public Exit(int[] ints) {
        super(ints);
    }

    public static boolean isOn() {
        return isOn;
    }

    @Override
    public Object[] execute(Object[] args) {
        System.out.println(Arrays.toString(new Save(new int[]{0}).execute(args)));
        isOn = false;
        try {
            DataBase.closeConnection();
        } catch (Exception e) {
            System.err.println("Error while closing connection with data base: " + e.getMessage());
        }
        return new Object[]{"Shutting down the server..."};
    }
}


package commands;

public class Exit extends Command {
    private static boolean isOn = true;

    public static boolean isOn() {
        return isOn;
    }

    @Override
    public String getDescription() {
        return "Завершение программы";
    }

    @Override
    public String execute() {
        System.out.println(new Save().execute());
        isOn = false;
        return "Shutting down the server...";
    }
}

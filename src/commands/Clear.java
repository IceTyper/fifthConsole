package commands;

public class Clear implements Command {
    @Override
    public String getDescription() {
        return "clear - очищение коллекции";
    }

    @Override
    public void execute(Core core) {
        System.out.println("Clear");
    }
}

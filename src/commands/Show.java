package commands;

public class Show implements Command {
    @Override
    public String getDescription() {
        return "show - Вывод элементов коллекции в строковом представлении";
    }

    @Override
    public void execute(Core core) {
        System.out.println("Show");
    }
}

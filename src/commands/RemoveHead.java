package commands;

public class RemoveHead implements Command {
    @Override
    public String getDescription() {
        return "remove_head - вывод первого элемента коллекции и его удаление";
    }

    @Override
    public void execute() {
        System.out.println("Remove head");
    }
}

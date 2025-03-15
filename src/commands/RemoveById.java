package commands;

public class RemoveById implements Command{
    @Override
    public String getDescription() {
        return "removes_by_id - удаление элемента из коллекции по его id";
    }

    @Override
    public void execute() {
        System.out.println("Remove by id");
    }
}

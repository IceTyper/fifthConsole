package commands;

public class RemoveLower implements Command{
    @Override
    public String getDescription() {
        return "remove_lower {element} - удаление из коллекции всех элементов, " +
                "меньше заданного";
    }

    @Override
    public void execute() {
        System.out.println("Remove lower");
    }
}

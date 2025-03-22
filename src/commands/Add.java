package commands;

public class Add implements Command {
    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    @Override
    public void execute(Core core) {

    }
}

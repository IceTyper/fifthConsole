package commands;
import Managers.Core;
public class Update implements Command {
    @Override
    public String getDescription() {
        return "update {id} {element} - обновление значения элемента коллекции, " +
                "id которого равен заданному";
    }

    @Override
    public void execute(Core core) {
        System.out.println("Update");
    }
}

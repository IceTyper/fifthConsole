package commands;


import exceptions.RedundantArgumentsException;
import io.Handler;

public class Add extends Command {

    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            Add add = new Add();
            add.setQueue(Handler.recordSpacemarineFields());

        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

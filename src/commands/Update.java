package commands;
import important.Core;

import java.util.ArrayList;
import java.util.Deque;

public class Update implements Command {
    @Override
    public String getDescription() {
        return "update {id} {element} - обновление значения элемента коллекции, " +
                "id которого равен заданному";
    }

    @Override
    public void execute(Core core, String[] args) {
        if (args.length == 0) {
            System.out.println("Вы не ввели айдишник");
        } else {
            int id = Integer.parseInt(args[0]);
            Deque<?> collection = core.getCollectionManager().getCollection();
            for (int i = 0; i < collection.size(); i++) {
                if (collection.getFirst().equals(id)) {
                ArrayList<?> arr = (ArrayList<?>) new ArrayList<>(collection).subList(0, i);

                }
            }
        }
    }
}

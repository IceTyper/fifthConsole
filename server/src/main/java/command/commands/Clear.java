package command.commands;

import command.AbstractCommand;
import command.Helpable;
import connection.User;

import java.util.ArrayList;

public class Clear extends AbstractCommand implements Helpable {
    public Clear(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        User user = (User) args[0];
        long rowsAffected = collectionHandler.clear(user.username());
        if (rowsAffected == 0) {
            response.add("Нечего удалять, а так хотелось :(");
        } else {
            response.add("Коллекция успешно очищена, удалено " + rowsAffected + " элементов.");
        }
        return response.toArray();
    }
}


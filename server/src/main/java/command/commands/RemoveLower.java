package command.commands;

import collection.CollectionHandable;
import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import connection.User;
import io.Builder;
import io.FieldsHandler;
import models.SpaceMarine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemoveLower extends AbstractCommand implements Helpable {

    public RemoveLower(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }

    @Override
    public Object[] execute(Object[] args) {
        if (args.length == 1) {
            return new Object[]{"RecordFields"};
        }
        if (!FieldsHandler.checkFields(Stream.of(args).skip(1).toArray())) return new Object[]{"PrintMessage", "Неверные поля для добавления элемента в коллекцию!"};

        long removed = removeHelper(args);
        if (removed > 0) {
            return new Object[]{"Удалено элементов: " + removed};
        } else {
            return new Object[]{"Нет элементов, меньших заданного (или нет принадлежащих вам)"};
        }
    }

    public static long removeHelper(Object[] args) {
        long counter = 0L;
        args[2] = Long.parseLong((String) args[2]);
        args[3] = Integer.parseInt((String) args[3]);
        args[4] = Long.parseLong((String) args[4]);
        args[5] = Boolean.parseBoolean((String) args[5]);
        args[6] = ((String) args[6]).toUpperCase();
        args[7] = ((String) args[7]).toUpperCase();
        args[9] = Long.parseLong((String) args[9]);
        ArrayList<Object> fields = new ArrayList<>(Stream.of(args).skip(1).toList());
        User user = (User) args[0];
        fields.add(user.username());
        SpaceMarine spacemarine = Builder.buildSpaceMarine(fields.toArray());
        Deque<SpaceMarine> collection = collectionHandler.getCollection();
        for (SpaceMarine marine : collection) {
            if (marine.compareTo(spacemarine) < 0 && (marine.getOwner() != null && marine.getOwner().equals(user.username()))) {
                collectionHandler.remove(marine);
                counter++;
            }
        }
        return counter;
    }
}

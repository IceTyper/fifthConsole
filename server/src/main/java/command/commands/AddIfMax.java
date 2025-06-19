package command.commands;

import collection.CollectionHandable;
import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import connection.User;
import io.Builder;
import io.FieldsHandler;
import models.SpaceMarine;

import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.Stream;

public class AddIfMax extends AbstractCommand implements Helpable {

    public AddIfMax(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
    }

    @Override
    public Object[] execute(Object[] args) {
        if (args.length == 1) {
            return new Object[]{"RecordFields"};
        }
        if (!FieldsHandler.checkFields(Stream.of(args).skip(1).toArray())) return new Object[]{"PrintMessage", "Неверные поля для добавления элемента в коллекцию!"};
        long id = addHelper(args);
        if (id != -1) {
            return new Object[]{"Элемент успешно добавлен в коллекцию! ID элемента: " + id};
        } else {
            return new Object[]{"Элемент не добавлен, так как не превышает максимальный элемент коллекции"};
        }
    }


    public static long addHelper(Object[] args) {
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
        SpaceMarine marine = Builder.buildSpaceMarine(fields.toArray());
        Deque<SpaceMarine> collection = collectionHandler.getCollection();
        boolean shouldAdd = collection.isEmpty() || collection.stream().allMatch(a -> marine.compareTo(a) > 0);
        if (shouldAdd) return collectionHandler.add(marine);
        return -1;
    }
}


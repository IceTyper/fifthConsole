package command.commands;

import collection.CollectionHandable;
import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import connection.User;
import io.Builder;
import io.FieldsHandler;
import models.MeleeWeapon;
import models.SpaceMarine;
import models.Weapon;

import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.Stream;

public class Add extends AbstractCommand implements Helpable {
    private static CollectionHandable handler = new CollectionHandler();

    public Add(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "add {element} : добавление нового элемента в коллекцию";
    }

    //name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world
    @Override
    public Object[] execute(Object[] args) {
        if (args.length == 1) {
            return new Object[]{"RecordFields"};
        }
        if (!FieldsHandler.checkFields(Stream.of(args).skip(1).toArray()))
            return new Object[]{"PrintMessage", "Неверные поля для добавления элемента в коллекцию!"};
        long id = addHelper(args);
        return id != -1 ? new Object[]{"Элемент успешно добавлен в коллекцию! ID элемента: " + id} : new Object[]{"Добавление элемента провалилось"};
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
        return collectionHandler.add(marine);
    }
}


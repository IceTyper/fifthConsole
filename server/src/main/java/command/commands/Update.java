package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import connection.User;
import io.Builder;
import io.FieldsHandler;
import models.SpaceMarine;

import java.util.Arrays;
import java.util.Deque;
import java.util.Optional;
import java.util.stream.Stream;

public class Update extends AbstractCommand implements Helpable {
    public Update(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public Object[] execute(Object[] args) {
        Deque<SpaceMarine> collection = collectionHandler.getCollection();
        long id;
        if (args[1] instanceof String s) {
            id = Long.parseLong(s);
        } else {
            id = (long) args[1];
        }

        Optional<SpaceMarine> marine = collection.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        User user = (User) args[0];

        if (args.length == 2) {
            if (marine.isPresent()) {
                if (!marine.get().getOwner().equals(user.username())) {
                    return new Object[]{"Элемент с таким id не принадлежит вам"};
                }
                return new Object[]{"RecordFields", id};
            }
            return new Object[]{"Элемента с таким id не существует, обновить нечего :("};
        }




        if (marine.isEmpty()) {
            return new Object[]{"Элемент куда-то испарился, простите, мы не знаем, что с ним стало 0_о"};
        }

        if (!FieldsHandler.checkFields(Stream.of(args).skip(2).toArray())) return new Object[]{"PrintMessage", "Неверные поля для изменения элемента"};

        SpaceMarine oldMarine = marine.get();
        if (oldMarine.getOwner() == null || !oldMarine.getOwner().equals(user.username())) {
            return new Object[]{"Этот элемент вам не принадлежит, вы не можете его обновить )K"};}

        Object[] newFields = Stream.concat(Arrays.stream(args).skip(2), Stream.of(user.username())).toArray();

        newFields[1] = Long.parseLong((String) newFields[1]);
        newFields[2] = Integer.parseInt((String) newFields[2]);
        newFields[3] = Long.parseLong((String) newFields[3]);
        newFields[4] = Boolean.parseBoolean((String) newFields[4]);
        newFields[5] = ((String) newFields[5]).toUpperCase();
        newFields[6] = ((String) newFields[6]).toUpperCase();
        newFields[8] = Long.parseLong((String) newFields[8]);

        SpaceMarine newMarine = Builder.buildSpaceMarine(newFields);
        newMarine.setId(oldMarine.getId());
        newMarine.setCreationDate(oldMarine.getCreationDate());
        boolean success = collectionHandler.updateElement(newMarine);
        if (success) return new Object[]{"Элемент успешно обновлён"};
        return new Object[]{"Не удалось обновить элемент"};
    }
}


package command.commands;

import command.AbstractCommand;
import models.SpaceMarine;
import collection.CollectionHandler;
import io.Builder;

import java.util.Deque;
import java.util.Optional;

public class Update extends AbstractCommand {
    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public Object[] execute(Object[] args) {
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();

        if (args.length == 1) {
            long id = (Long) args[0];
            boolean ifExists = collection.stream().
                    anyMatch(a -> a.getId() == id);
            return new Object[]{ifExists};
        }

        long id = (long) args[0];
        Optional<SpaceMarine> marine = collection.stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if (marine.isEmpty()) {
            return new Object[]{"Элемент куда-то испарился, простите, мы не знаем, что с ним стало 0_0"};
        }

        SpaceMarine oldMarine = marine.get();
        Builder builder = new Builder();
        // args[1..n] — новые поля
        Object[] newFields = new Object[args.length - 1];
        System.arraycopy(args, 1, newFields, 0, args.length - 1);
        SpaceMarine newMarine = builder.buildSpaceMarine(newFields);
        newMarine.setId(oldMarine.getId());
        newMarine.setCreationDate(oldMarine.getCreationDate());
        collection.remove(oldMarine);
        collection.addFirst(newMarine);

        return new Object[]{"Элемент успешно обновлён"};
    }
}

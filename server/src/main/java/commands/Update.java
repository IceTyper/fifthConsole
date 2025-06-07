package commands;

import collection.SpaceMarine;
import collection.utility.CollectionHandler;
import io.Builder;

import java.util.Deque;
import java.util.Optional;

public class Update extends Command {
    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public Object[] execute() {
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();

        if (queue.size() == 1) {
            long id = (Long) queue.remove();
            boolean ifExists = collection.stream().
                    anyMatch(a -> a.getId() == id);
            queue.clear();
            return new Object[]{ifExists};
        }

        long id = (long) queue.remove();
        Optional<SpaceMarine> marine = collection.stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if (marine.isEmpty()) {
            return new Object[]{false};
        }

        SpaceMarine oldMarine = marine.get();
        Builder builder = new Builder();
        SpaceMarine newMarine = builder.buildSpaceMarine(queue);
        newMarine.setId(oldMarine.getId());
        newMarine.setCreationDate(oldMarine.getCreationDate());
        collection.remove(oldMarine);
        collection.addFirst(newMarine);

        queue.clear();
        return new Object[]{"Элемент успешно обновлён"};
    }
}

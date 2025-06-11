package commands;

import models.SpaceMarine;
import collection.CollectionControllable;
import collection.CollectionHandler;
import io.Builder;

import java.util.Deque;

public class Add extends Command {
    private static CollectionControllable handler = new CollectionHandler();

    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    //name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world
    @Override
    public Object[] execute() {
        Builder builder = new Builder();
        Deque<SpaceMarine> collection = handler.getCollection();
        int size = collection.size();
        handler.getCollection().addFirst(builder.buildSpaceMarine(queue));
        long id = handler.getMaxId();
        queue.clear();
        return !(size == collection.size()) ? new Object[]{"Элемент успешно добавлен в коллекцию! ID элемента: " + id} : new Object[]{"Добавление элемента провалилось"};
    }
}

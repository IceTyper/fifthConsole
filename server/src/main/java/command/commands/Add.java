package command.commands;

import command.AbstractCommand;
import models.SpaceMarine;
import collection.CollectionHandable;
import collection.CollectionHandler;
import io.Builder;

import java.util.Deque;

public class Add extends AbstractCommand {
    private static CollectionHandable handler = new CollectionHandler();

    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    //name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world
    @Override
    public Object[] execute(Object[] args) {
        Builder builder = new Builder();
        Deque<SpaceMarine> collection = handler.getCollection();
        int size = collection.size();
        handler.getCollection().addFirst(builder.buildSpaceMarine(args));
        long id = handler.getMaxId();
        return !(size == collection.size()) ? new Object[]{"Элемент успешно добавлен в коллекцию! ID элемента: " + id} : new Object[]{"Добавление элемента провалилось"};
    }
}

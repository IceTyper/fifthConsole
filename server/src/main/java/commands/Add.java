package commands;

import utility.CollectionControllable;
import utility.CollectionHandler;
import models.*;
import utility.MessageSendable;
import utility.MessageSender;

public class Add extends Command {

    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    //name, x, y, health, loyal, weaponType, meleeWeaponType, chapterName, marinesCount, world
    @Override
    public void execute() {
        String name = (String) queue.remove();
        long x = (long) queue.remove();
        int y = (int) queue.remove();
        long health = (long) queue.remove();
        boolean loyal = (boolean) queue.remove();
        Weapon weapon = (Weapon) queue.remove();
        MeleeWeapon meleeWeapon = (MeleeWeapon) queue.remove();
        String chapterName = (String) queue.remove();
        long marinesCount = (long) queue.remove();
        String world = (String) queue.remove();
        CollectionControllable handler = new CollectionHandler();
        handler.addFirst(new SpaceMarine(name, new Coordinates(x, y), health, loyal, weapon, meleeWeapon, new Chapter(chapterName, marinesCount, world)));
        MessageSendable msg = new MessageSender();
        //msg.sendMessage(new String[]{"Элемент успешно добавлен в коллекицю!"});
    }
}

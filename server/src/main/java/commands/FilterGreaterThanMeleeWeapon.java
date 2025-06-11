package commands;

import models.MeleeWeapon;
import models.SpaceMarine;
import collection.CollectionHandler;

import java.util.ArrayList;
import java.util.Deque;

public class FilterGreaterThanMeleeWeapon extends Command {
    @Override
    public String getDescription() {
        return "filter_greater_than_melee_weapon {meleeWeapon} : вывести элементы, значение поля meleeWeapon которых больше заданного";
    }

    @Override
    public Object[] execute() {
        ArrayList<Object> response = new ArrayList<>();
        MeleeWeapon meleeWeapon = (MeleeWeapon) queue.remove();
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        for (SpaceMarine marine : collection) {
            MeleeWeapon mWeapon = marine.getMeleeWeapon();
            if (mWeapon != null && mWeapon.compareTo(meleeWeapon) > 0) {
                response.add(marine.toString());
            }
        }
        if (response.isEmpty()) {
            response.add("Ни один подходящий элемент не найден");
        }
        return response.toArray();
    }
}

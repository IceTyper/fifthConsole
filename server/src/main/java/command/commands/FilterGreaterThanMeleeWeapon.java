package command.commands;

import collection.CollectionHandler;
import command.AbstractCommand;
import command.Helpable;
import models.MeleeWeapon;
import models.SpaceMarine;

import java.util.ArrayList;
import java.util.Deque;

public class FilterGreaterThanMeleeWeapon extends AbstractCommand implements Helpable {
    public FilterGreaterThanMeleeWeapon(int[] ints) {
        super(ints);
    }

    @Override
    public String getDescription() {
        return "filter_greater_than_melee_weapon {meleeWeapon} : вывести элементы, значение поля meleeWeapon которых больше заданного";
    }

    @Override
    public Object[] execute(Object[] args) {
        ArrayList<Object> response = new ArrayList<>();
        String line = (String) args[1];
        MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeapon(line.toUpperCase());
        if (meleeWeapon == null) {
            response.add("Некорректное значение поля meleeWeapon");
            return response.toArray();
        }
        Deque<SpaceMarine> collection = new CollectionHandler().getCollection();
        for (SpaceMarine marine : collection) {
            MeleeWeapon mWeapon = marine.getMeleeWeapon();
            if (mWeapon != null && mWeapon.compareTo(meleeWeapon) > 0) {
                response.add("Удаляется элемент:");
                response.add(marine + "\n");
            }
        }
        if (response.isEmpty()) {
            response.add("Ни один подходящий элемент не найден");
        }
        return response.toArray();
    }
}

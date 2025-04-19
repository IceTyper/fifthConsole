package org.example.commands;

import org.example.Exceptions.RedundantArguments;
import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.MeleeWeapon;
import org.example.models.SpaceMarine;

import java.util.Arrays;

public class FilterGreaterThanMeleeWeapon implements Command {
    @Override
    public String getDescription() {
        return "filter_greater_than_melee_weapon {meleeWeapon} - вывод элементов, " +
                "значение поля meleeWeapon которых больше заданного";
    }

    @Override
    public void execute(Core core, String[] args) {
        try {
            if (args.length > 2) {
                throw new RedundantArguments();
            }
            boolean flag = false;
            MeleeWeapon meleeWeapon = MeleeWeapon.getMeleeWeapon(args[1].toUpperCase());
            CollectionManager<SpaceMarine> collectionManager = core.getCollectionManager();
            for (SpaceMarine spaceMarine : collectionManager.getCollection()) {
                if (spaceMarine.getMeleeWeapon().compareTo(meleeWeapon) > 0) {
                    flag = true;
                    System.out.println(spaceMarine);
                }
            }
            if (!flag) {
                System.out.println("Ни один подходящий элемент не найден");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Холодное оружие не введено");
        } catch (RedundantArguments e) {
            System.out.println(e.printProblem(args));
        } catch (Exception e) {
            System.out.println(Arrays.toString(args));
            System.out.println("Холодное оружие введено неверно или его вообще нет, попробуйте ещё раз");
            System.out.println("Доступные варианты: ");
            for (MeleeWeapon meleeWeapon : MeleeWeapon.values()) {
                System.out.print(meleeWeapon + " ");
            }
        }
    }
}

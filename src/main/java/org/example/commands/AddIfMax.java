package org.example.commands;

import org.example.important.CollectionManager;
import org.example.important.Core;
import org.example.interfaces.Command;
import org.example.models.SpaceMarine;

public class AddIfMax implements Command {
    @Override
    public String getDescription() {
        return "add_if_max {element} - добавление" +
                "нового элемента в коллекцию, если его значение превышает" +
                "значение наибольшего элемента этой коллекции";
    }

    @Override
    public void execute(Core core, String[] args) {
        CollectionManager<SpaceMarine> collectionManager = core.getCollectionManager();
        SpaceMarine marine = core.getBuilder().buildSpacemarine();
        if (marine.compareTo(collectionManager.getCollection().getFirst()) > 0) {
            collectionManager.addElement(marine);
            System.out.println("Элемент добавлен");
        } else {
            System.out.println("Элемент не соответствует условиям");
        }
    }
}

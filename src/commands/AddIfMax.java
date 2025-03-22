package commands;

import Managers.Core;

public class AddIfMax implements Command {
    @Override
    public String getDescription() {
        return "add_if_max {element} - добавление" +
                "нового элемента в коллекцию, если его значение превышает" +
                "значение наибольшего элемента этой коллекции";
    }

    @Override
    public void execute(Core core) {
        System.out.println("Add Max");
    }
}

package org.example.commands;

import org.example.important.Core;
import org.example.interfaces.Command;

public class Exit implements Command {
    @Override
    public String getDescription() {
        return "exit - завершение программы без сохранения в файл";
    }

    @Override
    public void execute(Core core, String[] args) {
        core.endCore();
        System.out.println("Прощай, юзер!");
    }
}

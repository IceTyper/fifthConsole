package command.commands;

import command.AbstractCommand;

public class Save extends AbstractCommand {
    public Save(int[] ints) {
        super(ints);
    }

    @Override
    public Object[] execute(Object[] args) {
        return new Object[]{"Всё гуд, всё сохранено"};
    }
}
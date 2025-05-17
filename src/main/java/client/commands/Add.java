package client.commands;


import client.exceptions.RedundantArgumentsException;
import client.io.Handler;

import java.util.Queue;

public class Add implements Command {
    private Queue<Object> queue;

    @Override
    public String getDescription() {
        return "add {element} - добавление нового элемента в коллекцию";
    }

    @Override
    public void execute(String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArgumentsException();
            }
            queue = Handler.recordSpacemarineFields();
            System.out.println(queue);
        } catch (RedundantArgumentsException e) {
            System.out.println(e.getMessage());
        }
    }
}

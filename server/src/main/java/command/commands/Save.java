package command.commands;

import command.AbstractCommand;
import io.FileHandable;
import io.FileHandler;

import java.io.IOException;

public class Save extends AbstractCommand {

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Object[] execute(Object[] args) {
        try {
            FileHandable fileHandler = new FileHandler();
            return new Object[]{fileHandler.saveToFile()};
        } catch (IOException e) {
            return new Object[]{"Saving to file failed: " + e.getMessage()};
        }
    }
}
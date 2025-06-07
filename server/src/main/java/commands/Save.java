package commands;

import io.FileHandable;
import io.FileHandler;

import java.io.IOException;

public class Save extends Command {

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Object[] execute() {
        try {
            FileHandable fileHandler = new FileHandler();
            return new Object[]{fileHandler.saveToFile()};
        } catch (IOException e) {
            return new Object[]{"Saving to file failed: " + e.getMessage()};
        }
    }
}
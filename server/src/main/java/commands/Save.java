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
    public String execute() {
        try {
            FileHandable fileHandler = new FileHandler();
            return fileHandler.saveToFile();
        } catch (IOException e) {
            return "Saving to file failed: " + e.getMessage();
        }
    }
}
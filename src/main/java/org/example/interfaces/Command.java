package org.example.interfaces;
import org.example.important.Core;

public interface Command {
    String getDescription();
    void execute(Core core, String[] args);
}

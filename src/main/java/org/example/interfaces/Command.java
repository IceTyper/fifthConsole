package org.example.interfaces;

import org.example.important.Core;

import java.util.Scanner;

public interface Command {
    boolean isExecuting = false;

    String getDescription();

    void execute(Core core, Scanner scanner, String[] args);
}

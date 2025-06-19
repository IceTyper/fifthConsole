package io;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOHandler implements IOHandable{
    private static Scanner defaultScanner = new Scanner(System.in);
    private static Scanner fileScanner = null;

    @Override
    public String readLine() throws NoSuchElementException {
        return (fileScanner != null) ? fileScanner.nextLine() : defaultScanner.nextLine();
    }

    @Override
    public boolean hasNextLine() {
        return (fileScanner != null) ? fileScanner.hasNextLine() : defaultScanner.hasNextLine();
    }

    @Override
    public void selectScanner(Scanner scanner) {
        fileScanner = scanner;
    }

    @Override
    public void selectConsoleScanner() {
        fileScanner = null;
    }

    @Override
    public String readConsoleLine() {
        return defaultScanner.nextLine();
    }

    @Override
    public boolean isReadingFromFile() {
        return fileScanner != null;
    }
}

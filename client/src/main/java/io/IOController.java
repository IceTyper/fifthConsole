package io;

import java.util.Scanner;

public class IOController implements IO {
    private static IOController instance;
    private Scanner fileScanner = null;
    private final Scanner defaultScanner = new Scanner(System.in);

    private IOController() {
    }

    public static IOController getInstance() {
        if (instance == null) {
            instance = new IOController();
        }
        return instance;
    }

    /**
     * @return
     */
    @Override
    public String readLine() {
        return (fileScanner != null) ? fileScanner.nextLine() : defaultScanner.nextLine();
    }

    /**
     * @return
     */
    @Override
    public boolean hasNextLine() {
        return (fileScanner != null) ? fileScanner.hasNextLine() : defaultScanner.hasNextLine();
    }

    /**
     * @param scanner
     */
    @Override
    public void selectScanner(Scanner scanner) {
        fileScanner = scanner;
    }

    /**
     *
     */
    @Override
    public void selectConsoleScanner() {
        fileScanner = null;
    }

    @Override
    public String readConsoleLine() {
        return defaultScanner.nextLine();
    }

    @Override
    public boolean isReadingFromFile() {return fileScanner != null;}
}

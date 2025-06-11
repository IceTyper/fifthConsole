package io;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//интерфейс для классов, отвечающих за ввод-вывод в консоли и файле
public interface IOHandable {
    String readLine() throws NoSuchElementException;

    boolean hasNextLine();

    void selectScanner(Scanner scanner);

    void selectConsoleScanner();

    String readConsoleLine();

    boolean isReadingFromFile();
}

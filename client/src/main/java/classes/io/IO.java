package classes.io;


import java.util.Scanner;

/**
 * Интерфейс, определяющий ввод и вывод (как консоль, так и файлы)
 * у наследуемого класса есть два поля Scanner:
 * private static Scanner fileScanner = null;
 * private static Scanner defaultScanner = new Scanner(System.in);
 * первый обозначает чтение из файла, то есть если
 * fileScanner = null, тогда читаем из консоли, то есть используем
 * defaultScanner, если fileScanner != null, значит нам подали другой
 * сканер через метод selectScanner(), который как раз читает
 * из какого-то файла
 */
public interface IO {
    String readLine();

    boolean hasNextLine();

    void selectScanner(Scanner obj);

    void selectConsoleScanner();

    String readConsoleLine();

    boolean isReadingFromFile();
}

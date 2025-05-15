package org.example.interfaces;

import org.example.important.CollectionManager;

import java.io.File;
import java.io.IOException;

/**
 * Интерфейс для управления превращением коллекции в json файл.
 * Определяет необходимые методы для классов, реализующих механизмы сохранения и загрузки данных в json.
 *
 * @author IceTyper
 */
public interface FileManagable {

    /**
     * Сохраняет состояние коллекции в указанный файл.
     * Реализация должна определять формат сериализации данных (JSON, XML и т.д.).
     *
     * @param file              Файл для сохранения данных
     * @param collectionManager Менеджер коллекции, предоставляющий данные для сохранения
     * @throws IOException При ошибках ввода-вывода
     */
    void saveToFile(File file, CollectionManager collectionManager) throws IOException;

    /**
     * Загружает состояние коллекции из указанного файла.
     * Реализация должна соответствовать формату, используемому в saveToFile().
     *
     * @param file              Файл для чтения данных (должен существовать и быть читаемым)
     * @param collectionManager Менеджер коллекции, в который будут загружены данные
     */
    void readFromFile(File file, CollectionManager collectionManager);
}
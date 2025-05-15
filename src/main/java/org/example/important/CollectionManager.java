package org.example.important;

import org.example.models.SpaceMarine;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

/**
 * Менеджер для работы с коллекцией объектов SpaceMarine.
 * Обеспечивает хранение, сортировку и базовые операции управления коллекцией.
 * Коллекция автоматически сортируется после любых изменений.
 * @author IceTyper
 * @see SpaceMarine
 */
public class CollectionManager {
    private final LocalDate creationDate;
    private Deque<SpaceMarine> collection;

    {
        creationDate = LocalDate.now();
    }

    /**
     * Конструктор менеджера коллекции
     * @param collection начальная коллекция объектов SpaceMarine (не null)
     */
    public CollectionManager(ArrayDeque<SpaceMarine> collection) {
        this.collection = collection;
    }

    /**
     * Возвращает дату создания коллекции
     * @return Неизменяемая дата инициализации коллекции
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает текущее состояние коллекции
     * @return Дек объектов SpaceMarine в отсортированном состоянии
     */
    public Deque<SpaceMarine> getCollection() {
        return collection;
    }

    /**
     * Заменяет текущую коллекцию новой
     * @param collection Новая коллекция объектов SpaceMarine (не null)
     */
    public void setCollection(Deque<SpaceMarine> collection) {
        this.collection = collection;
        sortCollection();
    }

    /**
     * Добавляет элемент в начало коллекции, а потом сортирует
     * @param element Добавляемый объект SpaceMarine (не null)
     */
    public void addElement(SpaceMarine element) {
        collection.addFirst(element);
        sortCollection();
    }

    /**
     * Возвращает тип используемой коллекции
     * @return Класс реализации коллекции
     */
    public Class<?> getCollectionType() {
        return collection.getClass();
    }

    /**
     * Сортирует коллекцию с использованием естественного порядка элементов.
     * Для сортировки используется реализация Comparable в классе SpaceMarine.
     * После сортировки коллекция преобразуется обратно в ArrayDeque.
     */
    public void sortCollection() {
        ArrayList<SpaceMarine> list = new ArrayList<>(collection);
        Collections.sort(list);
        collection = new ArrayDeque<>(list);
    }
}
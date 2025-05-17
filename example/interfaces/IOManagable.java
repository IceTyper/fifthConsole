package org.example.interfaces;

import org.example.important.Core;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Интерфейс для управления вводом-выводом и валидацией данных.
 * Определяет контракт для обработки пользовательского ввода, вывода сообщений
 *          и взаимодействия с командами приложения.
 */
public interface IOManagable {

    /**
     * Валидирует строку по заданному условию с повторным запросом ввода при ошибке.
     *
     * @param condition условие валидации строки
     * @return Валидная строка, соответствующая предикату
     * @throws IllegalArgumentException Если введённые данные не соответствуют условию
     */
    String validateString(Predicate<String> condition);

    /**
     * Выводит сообщение об ошибке в стандартный поток ошибок.
     *
     * @param errorMessage текст сообщения об ошибке
     */
    void printError(String errorMessage);

    /**
     * Валидирует числовое значение с преобразованием строки и проверкой условия.
     *
     * @param <T>       тип числового значения (наследник Number)
     * @param function  функция конвертации строки в число
     * @param condition условие валидации числового значения
     * @return Валидное число, соответствующее условию
     * @throws NumberFormatException При ошибке конвертации или нарушении условия
     */
    <T extends Number> T validateDigit(Function<String, T> function, Predicate<T> condition);

    /**
     * Преобразует строку в числовое значение без дополнительных проверок.
     *
     * @param <T>      тип числового значения (наследник Number)
     * @param function функция конвертации строки в число
     * @return Результат преобразования
     * @throws NumberFormatException При ошибке конвертации
     */
    <T extends Number> T getDigit(Function<String, T> function);

    /**
     * Выводит информационное сообщение в стандартный вывод.
     *
     * @param message текст сообщения
     */
    void printMessage(String message);

    /**
     * Возвращает текущий пользовательский ввод.
     *
     * @return Текущая строка ввода
     */
    String getUserInputInstance();

    /**
     * Устанавливает пользовательский ввод (используется для скриптов).
     *
     * @param input строка для эмуляции пользовательского ввода
     */
    void setUserInputInstance(String input);

    /**
     * Анализирует ввод и возвращает соответствующую команду.
     *
     * @param core ядро приложения для доступа к реестру команд
     * @return Объект команды или null если команда не найдена
     * @throws IllegalArgumentException При невалидном формате команды
     */
    Command checkInputForCommand(Core core);
}
package commands;

import exceptions.RedundantArgumentsException;

import java.io.Serializable;
import java.util.Queue;

/**
 * Абстрактный класс для реализации команд приложения.
 * Определяет базовый контракт для всех команд, включая описание и логику выполнения.
 * Каждая реализующая команда должна предоставить:
 * - текстовое описание для справки
 * - метод выполнения с обработкой аргументов
 *
 * @author IceTyper
 */
public abstract class Command implements Serializable {
    /**
     * Возвращает текстовое описание назначения и синтаксиса команды.
     * Используется для вывода справочной информации пользователю (через команду Help).
     *
     * @return Строка с описанием команды в формате:
     * "имя_команды {параметры} - описание функционала"
     */
    public abstract String getDescription();

    public abstract void execute(String[] args) throws RedundantArgumentsException;

}
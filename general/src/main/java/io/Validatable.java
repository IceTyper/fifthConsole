package io;

import java.util.function.Function;
import java.util.function.Predicate;

//интерфейс для валидаторов, которые ТОЧНО возвращают нужную строку
//или число в нужном формате
public interface Validatable {
    String validateString(Predicate<String> predicate);

    <T extends Number> String validateNumber(Function<String, T> function, Predicate<T> predicate);
}

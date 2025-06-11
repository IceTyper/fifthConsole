package command;

//интерфейс для всех команд
public interface Command {
    Object[] execute(Object[] args);
}

package commands;

public class SumOfHealth implements Command{
    @Override
    public String getDescription() {
        return "sum_of_health - вывод суммы значений поля health "+ "" +
                "для всех элементов коллекции";
    }

    @Override
    public void execute() {
        System.out.println("Sum of health");
    }
}

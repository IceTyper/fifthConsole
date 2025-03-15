package commands;

public class PrintFieldAscendingHealth implements Command {
    @Override
    public String getDescription() {
        return "print_field_ascending_health - вывод значений " +
                "поля health всех элементов в порядке возрастания";
    }

    @Override
    public void execute() {
        System.out.println("Printing field ascending health");
    }
}

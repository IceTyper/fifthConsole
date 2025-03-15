package commands;

public class FilterGreaterThanMeleeWeapon implements Command {
    @Override
    public String getDescription() {
        return "filter_greater_than_melee_weapon {meleeWeapon} - вывод элементов, " +
                "значение поля meleeWeapon которых больше заданного";
    }

    @Override
    public void execute() {
        System.out.println("Filter greater");
    }
}

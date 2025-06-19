package command.commands;

import command.AbstractCommand;
import connection.User;
import connectionchamber.Registration;
import exceptions.InvalidStringException;

public class RegisterUser extends AbstractCommand {

    public RegisterUser(int[] ints) {
        super(ints);
    }

    private static boolean check(User user) {
        return Registration.ifUserExists(user);
    }

    @Override
    public Object[] execute(Object[] args) throws InvalidStringException {
        if (!"5325c93c9e4697ff4395b23b9077123d".equals(args[1])) {
            return new Object[]{false};
        }
        User u = (User) args[0];
        User user = new User(u.username().trim(), u.password());
        String line = String.valueOf(args[2]);
        switch (line) {
            case "register":
                if (Registration.ifUsernameExists(user.username())) return new Object[]{false};
                return new Object[]{Registration.register(user.username(), user.password())};
            case "login":
                return new Object[]{check(user)};
            default:
                throw new InvalidStringException("неправильно введена команда: register_user");
        }
    }
}

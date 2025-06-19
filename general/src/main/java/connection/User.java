package connection;

import java.io.Serializable;

public record User(String username, String password) implements Serializable {
    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && username.equals(((User) obj).username);
    }

    @Override
    public String toString() {
        return "User: " + username;
    }
}

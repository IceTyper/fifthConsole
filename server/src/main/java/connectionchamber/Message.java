package connectionchamber;

import java.io.Serializable;
import java.util.Arrays;

public record Message(String commandName, Object[] args) implements Serializable {
    public String[] getMessage() {
        return Arrays.stream(args).
                map(Object::toString).
                toArray(String[]::new);
    }

    @Override
    public String toString() {
        return "Message{" +
                "commandName='" + commandName + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}

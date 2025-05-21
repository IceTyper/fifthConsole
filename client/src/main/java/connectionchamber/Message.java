package connectionchamber;

import java.io.Serializable;

public record Message(String commandName, Object[] args) implements Serializable {
}

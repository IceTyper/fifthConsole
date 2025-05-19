package utility;

import commands.Command;
import connectionchamber.ClientConnectable;

public interface CommandSendable {
    void sendCommandToServer(Command command, ClientConnectable client);
}

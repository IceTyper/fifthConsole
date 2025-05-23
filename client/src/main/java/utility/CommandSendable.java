package utility;

import commands.Command;
import connectionchamber.ClientConnectable;
import connectionchamber.Message;

public interface CommandSendable {
    void sendCommandToServer(Message msg, ClientConnectable client);
}

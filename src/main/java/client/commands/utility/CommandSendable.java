package client.commands.utility;

import client.commands.Command;
import client.connectionchamber.ClientConnectable;

public interface CommandSendable {
    void sendCommandToServer(Command command, ClientConnectable client);
}

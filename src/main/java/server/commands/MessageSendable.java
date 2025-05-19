package server.commands;

import server.connectionchamber.ServerConnectable;

public interface MessageSendable {
    Command acceptMessage(ServerConnectable server, String[] args);

    void sendMessage(ServerConnectable server, String[] args);
}

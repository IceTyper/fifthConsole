package commands;

import connection.Message;

import java.io.Serializable;


public abstract class Command implements Serializable {
    public abstract void execute(Message msg);
}
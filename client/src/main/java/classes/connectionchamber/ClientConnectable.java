package classes.connectionchamber;


import java.io.IOException;

//Базовый и самый абстрактный интерфейс для коннекта с сервером
public interface ClientConnectable {
    void connect(String host, int port) throws IOException;

    void send(byte[] data) throws IOException;

    byte[] receive() throws IOException;

    void close() throws IOException;
}

package net.javahub.musc.networking;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class MuscTCPClient {
    private Socket clientSocket;
    private BufferedInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        in = new BufferedInputStream(clientSocket.getInputStream());
    }

    public void readFile() throws IOException {
        byte[] file = in.readAllBytes();
        Files.write(Path.of("/home/martin/musc.zip"), file);
    }

    public void stopConnection() throws IOException {
        in.close();
        clientSocket.close();
    }
}

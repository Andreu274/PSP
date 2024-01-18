package Activitat6.activitat65;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1500);
            System.out.println("Servidor escoltant al port 1500...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connectat: " + clientSocket.getInetAddress());

                // Crear un nou fil per gestionar la connexió del client
                Thread fileTransferHandlerThread = new Thread(new ClientHandler(clientSocket));
                fileTransferHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


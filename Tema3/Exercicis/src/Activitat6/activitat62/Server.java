package Activitat6.activitat62;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Crear el servidor a l'escolta del port 2222
            ServerSocket serverSocket = new ServerSocket(2222);
            System.out.println("Servidor escoltant al port 2222...");

            while (true) {
                // Esperar a que arribi una connexió del client
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connectat.");

                // Crear un nou fil per gestionar la connexió del client
                Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

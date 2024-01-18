package Activitat6.activitat63;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static boolean exit = false;

    public static void main(String[] args) {
        int puertoDestino = 2222;
        try {
            ServerSocket serverSocket = new ServerSocket(puertoDestino);
            System.out.println("Servidor escoltant al port " + puertoDestino + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexió rebuda!");

                // Crear un nou fil per gestionar la connexió del client
                Thread clientHandlerThread = new Thread(new ClientHandler(clientSocket));
                clientHandlerThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error al crear el servidor.");
        }
    }
}


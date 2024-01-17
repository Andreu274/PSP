package Activitat1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Crear el servidor a l'escolta del port 2222
            ServerSocket serverSocket = new ServerSocket(2222);
            System.out.println("Servidor escoltant al port 2222...");

            // Esperar a que arribi una connexió del client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connectat.");

            // Configurar els fluxos d'entrada i sortida del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Llegir el text del client
            String clientText = in.readLine();
            System.out.println("Text rebut del client: " + clientText);

            // Convertir el text a majúscules
            String uppercaseText = clientText.toUpperCase();

            // Enviar el text en majúscules de tornada al client
            out.println(uppercaseText);
            System.out.println("Text en majúscules enviat al client: " + uppercaseText);

            // Tancar els recursos
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

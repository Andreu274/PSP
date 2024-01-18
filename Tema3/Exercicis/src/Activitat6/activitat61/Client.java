package Activitat6.activitat61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Establir la connexió amb el servidor al port 2222
            Socket socket = new Socket("localhost", 2222);
            System.out.println("Connexió establerta amb el servidor.");

            // Configurar els fluxos d'entrada i sortida del client
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Llegir el text desitjat per enviar al servidor
            System.out.print("Introduïu el text a enviar al servidor: ");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String clientText = userInput.readLine();

            // Enviar el text al servidor
            out.println(clientText);

            // Llegir la resposta en majúscules del servidor
            String serverResponse = in.readLine();
            System.out.println("Resposta del servidor en majúscules: " + serverResponse);

            // Tancar els recursos
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


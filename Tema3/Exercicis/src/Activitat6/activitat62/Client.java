package Activitat6.activitat62;

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

            // Començar el joc mostrant el missatge inicial del servidor
            String serverMessage = in.readLine();
            System.out.println(serverMessage);

            // Declarar la variable userInput per llegir l'entrada de l'usuari
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Jugar fins a endevinar el número secret o voler sortir
            boolean endGame = false;
            while (!endGame) {
                // Demanar a l'usuari que introdueixi un número
                System.out.print("Introdueix el teu número (o escriu 'exit' per sortir): ");
                String userGuess = userInput.readLine();

                // Enviar el número al servidor
                out.println(userGuess);

                // Llegir la resposta del servidor
                serverMessage = in.readLine();
                System.out.println(serverMessage);

                // Comprovar si s'ha endevinat el número secret o l'usuari vol sortir
                if (serverMessage.contains("Correcte") || userGuess.equalsIgnoreCase("exit")) {
                    endGame = true;
                }
            }

            // Tancar els recursos
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package Activitat6.activitat62;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private int secretNumber;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        // Generar un número secret aleatori entre 0 i 100 per a cada client
        Random random = new Random();
        this.secretNumber = random.nextInt(101);
    }

    @Override
    public void run() {
        try {
            // Configurar els fluxos d'entrada i sortida del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Inicialitzar el primer missatge al client
            out.println("Benvingut! Endevina el número secret entre 0 i 100.");

            // Iniciar el joc
            boolean endGame = false;
            while (!endGame) {
                // Llegir el número enviat pel client
                String clientGuess = in.readLine();
                if (clientGuess == null || clientGuess.equalsIgnoreCase("exit")) {
                    break;
                }

                // Convertir el número a enter
                int guess = Integer.parseInt(clientGuess);

                // Comprovar si és igual al número secret
                if (guess == secretNumber) {
                    out.println("Correcte! Has endevinat el número secret.");
                    endGame = true;
                } else {
                    // Comprovar si és menor o major que el número secret
                    if (guess < secretNumber) {
                        out.println("Incorrecte. El número secret és major. Intenta-ho de nou.\n");
                    } else {
                        out.println("Incorrecte. El número secret és menor. Intenta-ho de nou.\n");
                    }
                }
            }

            // Tancar els recursos
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

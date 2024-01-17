package Activitat2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) {
        try {
            // Crear el servidor a l'escolta del port 2222
            ServerSocket serverSocket = new ServerSocket(2222);
            System.out.println("Servidor escoltant al port 2222...");

            // Generar un número secret aleatori entre 0 i 100
            Random random = new Random();
            int secretNumber = random.nextInt(101);
            System.out.println("Número secret generat pel servidor: " + secretNumber);

            // Esperar a que arribi una connexió del client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connectat.");

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
                if (clientGuess == null) {
                    break;
                }

                // Convertir el número a enter
                int guess = Integer.parseInt(clientGuess);

                // Comprovar si és igual a 33
                if (guess == 33) {
                    out.println("Como? El 33? Vamooooos!");
                }
                // Comprovar si és igual a 69
                else if (guess == 69) {
                    out.println("⊂(0‿U)つ");
                }else if (guess == 5) {
                    out.println("Por el **** te la inco");
                } else {
                    // Comprovar si és igual, menor o major que el número secret
                    if (guess == secretNumber) {
                        out.println("Correcte! Has endevinat el número secret.");
                        endGame = true;
                    } else if (guess < secretNumber) {
                        out.println("Incorrecte. El número secret és major. Intenta-ho de nou.");
                    } else {
                        out.println("Incorrecte. El número secret és menor. Intenta-ho de nou.");
                    }
                }
            }

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

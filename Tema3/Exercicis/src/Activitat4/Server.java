package Activitat4;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2000);
            System.out.println("Servidor escoltant al port 2000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connectat des de: " + clientSocket.getInetAddress());

                // Crear objectes de flux de dades per llegir i escriure al client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Llegir l'operació i els números del client
                String ordre = reader.readLine();
                double num1 = Double.parseDouble(reader.readLine());
                double num2 = Double.parseDouble(reader.readLine());

                // Realitzar l'operació
                double resultat = 0.0;
                switch (ordre) {
                    case "suma":
                        resultat = num1 + num2;
                        break;
                    case "resta":
                        resultat = num1 - num2;
                        break;
                    case "multiplicacio":
                        resultat = num1 * num2;
                        break;
                    case "divisio":
                        resultat = num1 / num2;
                        break;
                    default:
                        writer.println("Operació no vàlida");
                        break;
                }

                // Enviar el resultat al client
                writer.println("Resultat: " + resultat);

                // Tancar els recursos per a aquest client específic
                reader.close();
                writer.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

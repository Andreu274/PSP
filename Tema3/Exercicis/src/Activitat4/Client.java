package Activitat4;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2000);

            // Crear objectes de flux de dades per llegir i escriure al servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Llegir l'operació des de la consola
            System.out.print("Introdueix l'operació (suma, resta, multiplicacio, divisio): ");
            String ordre = new BufferedReader(new InputStreamReader(System.in)).readLine();

            // Llegir els números des de la consola
            System.out.print("Introdueix el primer número: ");
            double num1 = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());

            System.out.print("Introdueix el segon número: ");
            double num2 = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());

            // Enviar l'operació i els números al servidor
            writer.println(ordre);
            writer.println(num1);
            writer.println(num2);

            // Llegir i imprimir la resposta del servidor
            String resposta = reader.readLine();
            System.out.println(resposta);

            // Tancar els recursos
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

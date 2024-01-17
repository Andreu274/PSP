package Activitat3;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2222);
            System.out.println("Servidor a l'escolta del port 2222...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connectat.");

                // Configurar el flujo de entrada del cliente
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String jsonData = out.println("OK");

                // Guardar el JSON en el archivo direcciones.txt
                guardarEnArchivo(jsonData);

                // Cerrar recursos
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarEnArchivo(String jsonData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("direcciones.txt", true))) {
            writer.write(jsonData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

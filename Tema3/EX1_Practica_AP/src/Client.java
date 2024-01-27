import java.io.*;
import java.net.Socket;

import org.json.JSONObject;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            boolean exitRequested = false;

            while (!exitRequested) {
                // Demanda al cliente que introduzca la operación
                System.out.println("Introdueix la operació (insert, select, delete, exit):");
                String command = userInput.readLine();
                out.println(command);

                switch (command) {
                    case "insert":
                        handleInsert(userInput, out, in);
                        break;
                    case "select":
                        handleSelect(userInput, out, in);
                        break;
                    case "delete":
                        handleDelete(userInput, out, in);
                        break;
                    case "exit":
                        exitRequested = true;
                        break;
                    default:
                        System.out.println("Comanda no valida.");
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleInsert(BufferedReader userInput, PrintWriter out, BufferedReader in) {
        try {
            System.out.println("Introdueix l'ID:");
            int id = Integer.parseInt(userInput.readLine());

            System.out.println("Introdueix el nom:");
            String nom = userInput.readLine();

            System.out.println("Introdueix el cognom:");
            String cognom = userInput.readLine();

            // Crear un objeto JSON con las datos del cliente
            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("command", "insert");
            jsonRequest.put("id", id);
            jsonRequest.put("nom", nom);
            jsonRequest.put("cognom", cognom);

            // Enviar el JSON al servidor
            out.println(jsonRequest.toString());

            // Leer la respuesta del servidor
            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleSelect(BufferedReader userInput, PrintWriter out, BufferedReader in) {
        try {
            System.out.println("Introdueix l'ID del registre que vols seleccionar:");
            int id = Integer.parseInt(userInput.readLine());
            out.println(id);

            // Llegeix la resposta del servidor
            System.out.println(in.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void handleDelete(BufferedReader userInput, PrintWriter out, BufferedReader in) {
        try {
            System.out.println("Introdueix l'ID del registre que vols eliminar:");
            int id = Integer.parseInt(userInput.readLine());
            out.println(id);

            // Llegeix la resposta del servidor
            System.out.println(in.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

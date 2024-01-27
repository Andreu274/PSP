import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server {
    private static final String DATABASE_FILE = "bbdd.txt";
    private static HashMap<Integer, String[]> database = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperant connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connectat.");

                // Crea un nou fil per gestionar la connexió amb el client
                Thread clientHandler = new Thread(() -> handleClient(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Llegeix la comanda del client
            String command = in.readLine();

            switch (command) {
                case "insert":
                    handleInsert(in, out);
                    break;
                case "select":
                    handleSelect(in, out);
                    break;
                case "delete":
                    handleDelete(in, out);
                    break;
                default:
                    out.println("Comanda no vàlida.");
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleInsert(BufferedReader in, PrintWriter out) {
        try {
            int id = Integer.parseInt(in.readLine());
            String nom = in.readLine();
            String cognom = in.readLine();

            if (database.containsKey(id)) {
                out.println("Ja existeix un registre amb aquest ID.");
            } else {
                String[] data = {nom, cognom};
                database.put(id, data);
                writeToDatabase();
                out.println("Registre inserit amb èxit.");
            }
        } catch (IOException | NumberFormatException e) {
            out.println("Error en llegir les dades del client.");
        }
    }

    private static void handleSelect(BufferedReader in, PrintWriter out) {
        try {
            int id = Integer.parseInt(in.readLine());

            if (database.containsKey(id)) {
                String[] data = database.get(id);
                out.println("ID: " + id + ", Nom: " + data[0] + ", Cognom: " + data[1]);
            } else {
                out.println("No s'ha trobat cap registre amb aquest ID.");
            }
        } catch (NumberFormatException e) {
            out.println("Error: L'ID ha de ser un nombre enter.");
        } catch (IOException e) {
            out.println("Error de lectura de l'ID del client.");
        }
    }


    private static void handleDelete(BufferedReader in, PrintWriter out) {
        try {
            int id = Integer.parseInt(in.readLine());

            if (database.containsKey(id)) {
                database.remove(id);
                writeToDatabase();
                out.println("Registre eliminat amb èxit.");
            } else {
                out.println("No s'ha trobat cap registre amb aquest ID.");
            }
        } catch (IOException | NumberFormatException e) {
            out.println("Error en llegir l'ID del client.");
        }
    }

    private static void writeToDatabase() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(DATABASE_FILE));

            for (int id : database.keySet()) {
                String[] data = database.get(id);
                writer.println(id + "," + data[0] + "," + data[1]);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/** La classe ChatServer implementa un servidor de xat bàsic utilitzant sockets a Java.
 * Utilitza un enfocament de servidor d'un sol fil que accepta connexions de clients i crea un fil separat (ClientHandler)
 * per gestionar cada client individualment. El servidor fa servir una estructura de dades HashMap anomenada
 * clients per fer un seguiment dels clients connectats, associant els seus identificadors (DNIs)
 * amb els seus corrents de sortida (PrintWriter). L'intercanvi de missatges entre clients es realitza utilitzant
 * la biblioteca Gson per convertir missatges en format JSON.*/

import java.io.*;
import java.net.*;
import java.util.HashMap;
import com.google.gson.Gson;

/**
 * Classe que implementa un servidor de xat bàsic.
 */
public class ChatServer {
    // Port utilitzat pel servidor
    private static final int PORT = 12345;

    // Mapa per fer el seguiment dels clients connectats
    private static HashMap<String, PrintWriter> clients = new HashMap<>();

    // Objecte Gson per convertir objectes a format JSON i viceversa
    private static Gson gson = new Gson();

    /**
     * Mètode principal que inicialitza el servidor i espera connexions de clients.
     */
    public static void main(String[] args) {
        try {
            // Inicialitza el servidor amb el port especificat
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciat. Esperant clients...");

            // Bucle principal que espera connexions de clients
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Classe interna que gestiona les comunicacions amb un client específic.
     */
    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientDni;

        /**
         * Constructor que inicialitza les comunicacions amb el client.
         */
        public ClientHandler(Socket socket) {
            clientSocket = socket;

            try {
                // Inicialitza els corrents d'entrada i sortida amb el client
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Rep el DNI del client al connectar-se i l'associa amb el seu PrintWriter
                clientDni = in.readLine();
                clients.put(clientDni, out);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Mètode que retransmet els missatges a tots els clients connectats.
         */
        private void broadcastMessage(Message message) {
            for (PrintWriter clientOut : clients.values()) {
                clientOut.println(gson.toJson(message));
            }
        }

        /**
         * Mètode principal del fil que gestiona el client.
         */
        @Override
        public void run() {
            try {
                // Bucle principal que escolta els missatges del client
                while (true) {
                    String jsonMessage = in.readLine();
                    Message message = gson.fromJson(jsonMessage, Message.class);

                    // Si el missatge és "adeu", el client es desconnecta i es treu del mapa clients
                    if (message.getText().equalsIgnoreCase("adeu")) {
                        clients.remove(clientDni);
                        break;
                    }

                    // Retransmet el missatge a tots els clients connectats
                    broadcastMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // Tanca els recursos associats al client (corrents i socket)
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

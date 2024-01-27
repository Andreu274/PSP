import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ServidorXat {
    private Map<String, ObjectOutputStream> clients = new HashMap<>();

    public static void main(String[] args) {
        new ServidorXat().iniciaServidor();
    }

    public void iniciaServidor() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                String dni = inputStream.readUTF();

                clients.put(dni, new ObjectOutputStream(clientSocket.getOutputStream()));

                System.out.println("Nou client connectat amb DNI: " + dni);

                new Thread(new GestorXat(clientSocket, dni)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class GestorXat implements Runnable {
        private Socket clientSocket;
        private String dni;

        public GestorXat(Socket clientSocket, String dni) {
            this.clientSocket = clientSocket;
            this.dni = dni;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                while (true) {
                    String missatge = inputStream.readUTF();
                    if (missatge.equals("adeu")) {
                        clients.remove(dni);
                        clientSocket.close();
                        break;
                    }

                    // Processa el missatge i envia la resposta als clients
                    processaMissatge(missatge);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void processaMissatge(String missatge) throws IOException {
            // Aquí pots implementar la lògica per gestionar el xat i emmagatzemar-ho en un fitxer
            // En aquest exemple, simplement l'enviem de nou als clients involucrats
            for (ObjectOutputStream clientStream : clients.values()) {
                clientStream.writeUTF(dni + ": " + missatge);
                clientStream.flush();
            }
        }
    }
}

package Activitat6.activitat65;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Llegir el nom del fitxer del client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String fileName = reader.readLine();

            // Verificar si el fitxer existeix
            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                // Enviar el fitxer al client
                sendFile(clientSocket, file);
            } else {
                // Enviar missatge d'error al client
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println("Error: El fitxer no existeix");
                writer.close();
            }

            // Tancar recursos
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendFile(Socket clientSocket, File file) {
        try {
            // Enviar el nom del fitxer i la seva mida al client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(file.getName());
            writer.println(file.length());

            // Enviar el contingut del fitxer al client
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Tancar recursos
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

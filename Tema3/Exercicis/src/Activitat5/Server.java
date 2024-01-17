package Activitat5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1500);
            System.out.println("Servidor escuchando en el puerto 1500...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Leer el nombre del archivo del cliente
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String fileName = reader.readLine();

                // Verificar si el archivo existe
                File file = new File(fileName);
                if (file.exists() && file.isFile()) {
                    // Enviar el archivo al cliente
                    sendFile(clientSocket, file);
                } else {
                    // Enviar mensaje de error al cliente
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    writer.println("Error: El archivo no existe");
                    writer.close();
                }

                // Cerrar recursos
                reader.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendFile(Socket clientSocket, File file) {
        try {
            // Enviar el nombre del archivo y su tamaÃ±o al cliente
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(file.getName());
            writer.println(file.length());

            // Enviar el contenido del archivo al cliente
            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Cerrar recursos
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
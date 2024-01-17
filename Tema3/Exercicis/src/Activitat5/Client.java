package Activitat5;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1500);

            // Solicitar el nombre del archivo al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese el nombre del archivo en el servidor: ");
            String fileName = scanner.nextLine();

            // Enviar el nombre del archivo al servidor
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(fileName);

            // Leer la respuesta del servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();

            if (response.startsWith("Error")) {
                System.out.println(response);
            } else {
                // Leer el nombre y el tamaÃ±o del archivo
                String receivedFileName = reader.readLine();
                long fileSize = Long.parseLong(reader.readLine());

                // Recibir el contenido del archivo y mostrarlo por pantalla
                receiveAndDisplayFile(socket, receivedFileName, fileSize);
            }

            // Cerrar recursos
            scanner.close();
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void receiveAndDisplayFile(Socket socket, String fileName, long fileSize) throws IOException {
        InputStream inputStream = socket.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        byte[] buffer = new byte[4096];
        int bytesRead;
        long totalBytesRead = 0;

        while (totalBytesRead < fileSize && (bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
        }

        // Cerrar recursos
        fileOutputStream.close();

        // Mostrar el contenido del archivo por pantalla
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = fileReader.readLine()) != null) {
            System.out.println(line);
        }

        // Cerrar recursos
        fileReader.close();
    }
}
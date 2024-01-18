package Activitat6.activitat65;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1500);

            // Sol·licitar el nom del fitxer a l'usuari
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdueix el nom del fitxer al servidor: ");
            String fileName = scanner.nextLine();

            // Enviar el nom del fitxer al servidor
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(fileName);

            // Llegir la resposta del servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();

            if (response.startsWith("Error")) {
                System.out.println(response);
            } else {
                // Llegir el nom i la mida del fitxer
                String receivedFileName = reader.readLine();
                long fileSize = Long.parseLong(reader.readLine());

                // Rebre el contingut del fitxer i mostrar-lo per pantalla
                receiveAndDisplayFile(socket, receivedFileName, fileSize);
            }

            // Tancar recursos
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

        // Tancar recursos
        fileOutputStream.close();

        // Mostrar el contingut del fitxer per pantalla
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = fileReader.readLine()) != null) {
            System.out.println(line);
        }

        // Tancar recursos
        fileReader.close();
    }
}

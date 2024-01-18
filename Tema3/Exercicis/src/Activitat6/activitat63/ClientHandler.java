package Activitat6.activitat63;

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
            // Configurar els fluxos d'entrada i sortida del servidor
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bf = new BufferedReader(isr);

            String linea = bf.readLine();

            // Escriure al fitxer
            try (FileWriter fileWriter = new FileWriter("Activitat3.json");) {
                fileWriter.write(linea);
                System.out.println("Fitxer creat amb les dades rebudes.");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Resposta al client
            OutputStream os = clientSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("OK\n");
            pw.flush();

            // Tancar recursos
            is.close();
            isr.close();
            bf.close();
            os.close();
            pw.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Error en la gestió del client.");
        }
    }
}


package Activitat6.activitat61;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Llegir el text del client
            String clientText = in.readLine();
            System.out.println("Text rebut del client: " + clientText);

            // Convertir el text a majúscules
            String uppercaseText = clientText.toUpperCase();

            // Enviar el text en majúscules de tornada al client
            out.println(uppercaseText);
            System.out.println("Text en majúscules enviat al client: " + uppercaseText);

            // Tancar els recursos
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

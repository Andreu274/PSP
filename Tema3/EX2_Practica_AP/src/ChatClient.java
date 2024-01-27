/**
 La classe ChatClient implementa un client de xat en Java que es connecta a un servidor i envia/rebuda missatges en temps real.
 El DNI del client s'utilitza com a identificador en les comunicacions. El client escolta continuament els missatges del servidor
 i permet als usuaris escriure i enviar missatges al xat fins que decideixen desconnectar-se amb "adeu".
 La connexió amb el servidor es tanca en aquest punt.*/


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe que implementa un client per al xat.
 */
public class ChatClient {
    // DNI del client
    private final String myDni;

    // Objecte Gson per convertir objectes a format JSON i viceversa
    private final Gson gson;

    /**
     * Constructor que inicialitza les dades del client.
     */
    public ChatClient(String myDni) {
        this.myDni = myDni;
        this.gson = new Gson();
    }

    /**
     * Mètode que inicia la comunicació amb el servidor.
     */
    public void start() {
        try {
            // Estableix una connexió amb el servidor utilitzant el localhost i el port especificat
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Envia el DNI al servidor
            out.println(myDni);

            // Fil per escoltar i mostrar els missatges rebuts
            new Thread(() -> {
                try {
                    while (true) {
                        String receivedMessage = in.readLine();
                        Message received = gson.fromJson(receivedMessage, Message.class);
                        System.out.println(received.getDni1() + ": " + received.getText());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            Scanner scanner = new Scanner(System.in);

            // Imprimeix la sol·licitud només la primera vegada
            System.out.print("Escriu el teu missatge (o 'adeu' per sortir): ");
            String text = scanner.nextLine();

            while (true) {
                // Imprimeix el guió ("-") només després de la primera vegada
                System.out.print("- ");
                text = scanner.nextLine();

                // Envia el missatge amb myDni com a dni1
                Message message = new Message(myDni, "All", text);
                String jsonMessage = gson.toJson(message);
                out.println(jsonMessage);

                if (text.equalsIgnoreCase("adeu")) {
                    break;
                }
            }

            // Tanca la connexió amb el servidor
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

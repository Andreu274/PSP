package Activitat6.activitat63;

import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static BufferedReader getFlujo(InputStream is) {
        InputStreamReader isr = new InputStreamReader(is);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) {
        String destino = "localhost";
        int puertoDestino = 2222;
        Socket socket = new Socket();
        InetSocketAddress direccion = new InetSocketAddress(destino, puertoDestino);

        Scanner sc = new Scanner(System.in);

        System.out.println("Escriu un carrer");
        String carrer = sc.nextLine();
        System.out.println("Escriu codi postal");
        String cp = sc.nextLine();
        System.out.println("Escriu pais");
        String pais = sc.nextLine();
        System.out.println("Escriu numero casa.");
        String casa = sc.nextLine();

        JSONObject json = new JSONObject();
        json.put("carrer", carrer);
        json.put("cp", cp);
        json.put("pais", pais);
        json.put("casa", casa);
        System.out.println(json);

        try {
            socket.connect(direccion);

            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.print(json + "\n");
            pw.flush();

            BufferedReader bfr = Client.getFlujo(socket.getInputStream());
            System.out.println("Resposta del servidor: " + bfr.readLine());

            // Tancar recursos
            pw.close();
            bfr.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al connectar amb el servidor.");
        }
    }
}

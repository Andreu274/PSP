package Activitat3;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Conectar al servidor en el puerto 2222
            Socket socket = new Socket("localhost", 2222);

            // Configurar el flujo de salida del cliente
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Generar el JSON y enviarlo al servidor
            JSONObject json = generarJSON();
            out.println(json.toJSONString());

            // Cerrar recursos
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject generarJSON() {
        JSONObject json = new JSONObject();
        json.put("Carrer", "Calle A");
        json.put("CodiPostal", "08001");
        json.put("Pais", "España");
        json.put("NombreCasa", "Casa 123");
        return json;
    }
}

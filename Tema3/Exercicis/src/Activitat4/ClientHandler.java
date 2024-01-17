package Activitat4;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Leer la operación y los números del cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String operacion = reader.readLine();
            double num1 = Double.parseDouble(reader.readLine());
            double num2 = Double.parseDouble(reader.readLine());

            // Realizar la operación
            double resultado = realizarOperacion(operacion, num1, num2);

            // Enviar el resultado al cliente
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println("Resultado: " + resultado);

            // Cerrar recursos
            reader.close();
            writer.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double realizarOperacion(String operacion, double num1, double num2) {
        switch (operacion) {
            case "suma":
                return num1 + num2;
            case "resta":
                return num1 - num2;
            case "multiplicacion":
                return num1 * num2;
            case "division":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    System.out.println("Error: División por cero");
                    return Double.NaN;
                }
            default:
                System.out.println("Error: Operación no reconocida");
                return Double.NaN;
        }
    }
}

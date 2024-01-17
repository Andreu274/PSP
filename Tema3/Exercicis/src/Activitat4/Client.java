package Activitat4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2000);

            // Solicitar operación y números al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese la operación (suma, resta, multiplicacion, division): ");
            String operacion = scanner.nextLine();
            System.out.print("Ingrese el primer número: ");
            double num1 = Double.parseDouble(scanner.nextLine());
            System.out.print("Ingrese el segundo número: ");
            double num2 = Double.parseDouble(scanner.nextLine());

            // Enviar la operación y los números al servidor
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(operacion);
            writer.println(num1);
            writer.println(num2);

            // Leer el resultado del servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String resultado = reader.readLine();
            System.out.println(resultado);

            // Cerrar recursos
            scanner.close();
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

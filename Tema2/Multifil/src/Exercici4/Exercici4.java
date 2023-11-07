package Exercici4;

import java.util.Scanner;

public class Exercici4 {
    public static void main(String[] args) {
        System.out.println("Introdueix 'exit' per interrompre el càlcul de Fibonacci.");
        CalculFibonacci filCalcul = new CalculFibonacci();
        filCalcul.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                filCalcul.interrompre();
                System.out.println("Interrupció solicitada. Espera a que el fil de càlcul finalitzi...");
                break;
            }
        }

        System.out.println("Programa principal finalitzat.");
        scanner.close();
    }
}

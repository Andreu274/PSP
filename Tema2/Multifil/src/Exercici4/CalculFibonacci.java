package Exercici4;

import java.util.Scanner;

class CalculFibonacci extends Thread {
    private boolean interromput = false;

    public void run() {
        int n1 = 1;
        int n2 = 1;
        Scanner scanner = new Scanner(System.in);

        while (!interromput) {
            int suma = n1 + n2;
            System.out.println(suma);
            n1 = n2;
            n2 = suma;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Ignora aquesta excepció
            }
        }

        System.out.println("Fil de càlcul interromput. Procés finalitzat.");
        scanner.close();
    }

    public void interrompre() {
        interromput = true;
    }
}
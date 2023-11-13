package Exercici7;

import java.util.Scanner;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix el nombre de segons: ");
        int segons = scanner.nextInt();

        CompteEnrereThread compteEnrereThread = new CompteEnrereThread(segons);
        AvisThread avisThread = new AvisThread(compteEnrereThread);

        compteEnrereThread.start();
        avisThread.start();

        try {
            compteEnrereThread.join();
            avisThread.join();
        } catch (InterruptedException e) {
            System.out.println("Programa interromput des del pare.");
        }

        System.out.println("Programa finalitzat.");
    }
}

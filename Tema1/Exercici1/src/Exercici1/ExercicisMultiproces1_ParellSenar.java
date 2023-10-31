package Exercici1;

import java.util.Scanner;

public class ExercicisMultiproces1_ParellSenar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("");
            System.out.println("=================================================");
            System.out.println("Menú:");
            System.out.println("1. Comprovar si un nombre és Parell o Senar");
            System.out.println("2. Sortir del programa");
            System.out.println("Selecciona una opció: ");
            System.out.println("=================================================");

            int opcio = scanner.nextInt();

            switch (opcio) {
                case 1:
                    // Comprovar si un número és parell o senar
                    System.out.print("Introdueix un nombre enter positiu: ");
                    try {
                        int numero = scanner.nextInt();
                        String resultat = (numero % 2 == 0) ? "Parell" : "Senar";
                        System.out.println();
                        System.out.println("El nombre " + numero + " és " + resultat + "");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Si us plau, introdueixi un nombre enter positiu vàlid.");
                        scanner.nextLine(); // Fer net el buffer de teclat
                    }
                    break;
                case 2:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opció no vàlida. Si us plau, selecciona una opció vàlida.");
            }
        }

        System.out.println("Au idoi, adeu.");
        scanner.close();
    }
}

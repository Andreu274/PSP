package Exercici9;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdueix un text:");
        String text = scanner.nextLine();

        Semaphore semaphore = new Semaphore(1); // Semàfor per controlar l'accés al fitxer

        VocalThread[] fils = new VocalThread[5];
        char[] vocals = {'a', 'e', 'i', 'o', 'u'};
        String rutaFitxer = "resultat.txt"; // Canvia la ruta segons les teves necessitats

        // Crear i executar els fils
        for (int i = 0; i < 5; i++) {
            fils[i] = new VocalThread(text, vocals[i], rutaFitxer, semaphore);
            fils[i].start();
        }

        // Esperar que tots els fils hagin acabat
        for (VocalThread fil : fils) {
            try {
                fil.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el contingut del fitxer per pantalla
        System.out.println("Contingut de l’arxiu resultant:");
        try {
            Scanner fileScanner = new Scanner(new java.io.File(rutaFitxer));
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


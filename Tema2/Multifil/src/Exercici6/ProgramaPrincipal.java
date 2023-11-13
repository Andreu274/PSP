package Exercici6;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        try {
            Fil1 fil1 = new Fil1(5); // Indica la cantidad de números que mostrará Fil1
            Fil2 fil2 = new Fil2(fil1);

            fil1.start();
            fil2.start();

            // Espera a que ambos hilos terminen
            fil1.join();
            fil2.join();

            System.out.println("Programa finalitzat.");
        } catch (InterruptedException e) {
            System.out.println("Programa interromput des del pare.");
        }
    }
}






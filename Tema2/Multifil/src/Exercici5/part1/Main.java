package Exercici5.part1;

public class Main {
    public static void main(String[] args) {

        // Crear fils per "TIC" i "TAC"
        Thread ticThread = new Thread(new TicTacPrinter("TIC"));
        Thread tacThread = new Thread(new TicTacPrinter("TAC"));

        // Iniciar els fils
        ticThread.start();
        tacThread.start();

        try {
            // Esperar a que els fils acabin
            ticThread.join();
            tacThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa finalizat"); // Missatge al finalitzar
    }
}
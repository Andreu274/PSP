package Exercici52;

public class Main {
    public static void main(String[] args) {


        // Crear hilos para "TIC" y "TAC"
        Thread ticThread = new Thread(new TicTacPrinterRandom("TIC"));
        Thread tacThread = new Thread(new TicTacPrinterRandom("TAC"));

        // Iniciar los hilos
        ticThread.start();
        tacThread.start();
        System.out.println("Programa finalizado"); // Mensaje al finalizar
    }
}
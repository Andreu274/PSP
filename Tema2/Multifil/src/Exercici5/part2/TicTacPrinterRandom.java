package Exercici5.part2;

import java.util.Random;

class TicTacPrinterRandom implements Runnable {
    private String message;
    private Random random; // Objeto para generar números aleatorios

    public TicTacPrinterRandom(String message) {
        this.message = message;

        this.random = new Random();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(message); // Imprimir el mensaje actual ("TIC" o "TAC")

            // Generar un número aleatorio entre 100 y 500 para la pausa en milisegundos
            int sleepTime = random.nextInt(401) + 100;
            try {
                Thread.sleep(sleepTime); // Esperar un tiempo aleatorio antes de continuar con el siguiente mensaje
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

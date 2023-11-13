package Exercici10;

import java.util.concurrent.Semaphore;

public class TicTacTocThread extends Thread {
    private String mensaje;
    private Semaphore actualSem;
    private Semaphore siguienteSem;

    public TicTacTocThread(String mensaje, Semaphore actualSem, Semaphore siguienteSem) {
        this.mensaje = mensaje;
        this.actualSem = actualSem;
        this.siguienteSem = siguienteSem;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                actualSem.acquire();
                System.out.println(mensaje);
                siguienteSem.release();
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package Exercici8;

import java.util.concurrent.Semaphore;

public class Comptador {
    private int valor = 0;
    private Semaphore semaphore = new Semaphore(1);

    public int getValor() {
        return valor;
    }

    public void incrementar() {
        try {
            semaphore.acquire();
            valor++;
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


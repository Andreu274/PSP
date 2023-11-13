package Exercici6;

import java.util.Random;

class Fil1 extends Thread {
    private int cantidad;

    public Fil1(int cantidad) {
        this.cantidad = cantidad;
    }

    public void run() {
        Random rand = new Random();
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Fil1: " + rand.nextInt(100));
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Fil1 interromput.");
                return;
            }
        }
        System.out.println("Fil1 ha acabat la seva execució.");
    }
}

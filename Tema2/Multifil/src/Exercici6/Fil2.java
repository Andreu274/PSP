package Exercici6;

import java.util.Random;

class Fil2 extends Thread {
    private Fil1 fil1;

    public Fil2(Fil1 fil1) {
        this.fil1 = fil1;
    }

    public void run() {
        Random rand = new Random();
        while (fil1.isAlive()) {
            System.out.println("Fil2: " + rand.nextInt(100));
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Fil2 interromput.");
                return;
            }
        }
        System.out.println("Fil2 ha acabat la seva execució.");
    }
}
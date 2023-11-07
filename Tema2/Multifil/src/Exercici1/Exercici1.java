package Exercici1;

public class Exercici1 {
    public static void main(String[] args) {
        int N = 5; // Número de hilos a crear

        for (int i = 1; i <= N; i++) {
            Thread hilo = new Thread(new Fil(i));
            hilo.start();
        }
    }
}


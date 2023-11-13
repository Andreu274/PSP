package Exercici8;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        int N = 10; // Número de hilos sumadores

        Comptador comptador = new Comptador();
        Sumador[] sumadores = new Sumador[N];

        // Crear y iniciar los hilos sumadores
        for (int i = 0; i < N; i++) {
            sumadores[i] = new Sumador(comptador, i);
            sumadores[i].start();
        }

        // Esperar a que todos los hilos sumadores terminen
        for (int i = 0; i < N; i++) {
            try {
                sumadores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

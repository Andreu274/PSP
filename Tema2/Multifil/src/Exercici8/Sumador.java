package Exercici8;

public class Sumador extends Thread {
    private Comptador comptador;
    private int numFil;

    public Sumador(Comptador comptador, int numFil) {
        this.comptador = comptador;
        this.numFil = numFil;
    }

    public void run() {
        try {
            Thread.sleep((long) (Math.random() * 1000)); // Espera aleatoria para simular procesamiento
            int valorAnterior = comptador.getValor();

            comptador.incrementar();

            System.out.println("Fil " + numFil + " - Nou valor del comptador: " + comptador.getValor());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


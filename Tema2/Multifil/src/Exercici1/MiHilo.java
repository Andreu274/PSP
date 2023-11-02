package Exercici1;

class MiHilo implements Runnable {
    private int numeroHilo;

    public MiHilo(int numeroHilo) {
        this.numeroHilo = numeroHilo;
    }

    @Override
    public void run() {
        System.out.println("Fil " + numeroHilo + ": iniciat");

        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        System.out.println("Fil " + numeroHilo + ": Valor " + numeroAleatorio);

        System.out.println("Fil " + numeroHilo + ": finalitzat");
    }
}

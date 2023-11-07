package Exercici1;

class Fil implements Runnable {
    private int numeroFil;

    public Fil(int numerofil) {
        this.numeroFil = numerofil;
    }

    @Override
    public void run() {
        System.out.println("Fil " + numeroFil + ": iniciat");

        int numeroAleatorio = (int) (Math.random() * 100) + 1;
        System.out.println("Fil " + numeroFil + ": Valor " + numeroAleatorio);


        System.out.println("Fil " + numeroFil + ": finalitzat");
    }
}

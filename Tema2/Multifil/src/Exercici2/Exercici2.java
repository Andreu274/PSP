package Exercici2;

public class Exercici2 {
    public static void main(String[] args) {
        Operacions operacions = new Operacions();

        Thread filSuma = new Thread(() -> {
            operacions.suma();
        });

        Thread filResta = new Thread(() -> {
            operacions.resta();
        });

        filSuma.start();
        filResta.start();

        try {
            filSuma.join();
            filResta.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final de compte: " + operacions.getCompte());
    }
}

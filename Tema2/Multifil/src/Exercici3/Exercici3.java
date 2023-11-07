package Exercici3;

public class Exercici3 {
    public static void main(String[] args) {
        prova1();
        prova2();
    }

    public static void prova1() {
        System.out.println("Prova 1:");
        Factorial fil1 = new Factorial("Fil 1", Thread.MIN_PRIORITY);
        Factorial fil2 = new Factorial("Fil 2", Thread.MAX_PRIORITY);

        fil1.start();
        fil2.start();
    }

    public static void prova2() {
        System.out.println("Prova 2:");
        for (int i = 1; i <= 10; i++) {
            int prioritat = (i <= 5) ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Factorial fil = new Factorial("Fil " + i, prioritat);
            fil.start();
        }
    }
}

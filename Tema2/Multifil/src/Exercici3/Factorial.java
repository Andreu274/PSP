package Exercici3;

class Factorial extends Thread {
    private String nomFil;
    private int prioritat;

    public Factorial(String nomFil, int prioritat) {
        this.nomFil = nomFil;
        this.prioritat = prioritat;
        this.setPriority(prioritat);
    }

    public void run() {
        int n = 10; // Nombre pel càlcul de Fibonacci
        int resultat = calcularFibonacci(n);
        System.out.println(nomFil + " - " + Thread.currentThread().getPriority() + ": " + resultat);
    }

    private int calcularFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
    }
}

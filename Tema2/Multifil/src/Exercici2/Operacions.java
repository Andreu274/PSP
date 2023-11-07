package Exercici2;

public class Operacions {
    private int[] compte = {20};

    public synchronized void suma() {
        int registre = compte[0];
        System.out.println("Fil suma => compte: " + registre);
        registre += 10;
        System.out.println("Fil suma => registre: " + registre);
        compte[0] = registre;
        System.out.println("Fil suma => compte: " + compte[0]);
    }

    public synchronized void resta() {
        int registre = compte[0];
        System.out.println("Fil resta => compte: " + registre);
        registre -= 10;
        System.out.println("Fil resta => registre: " + registre);
        compte[0] = registre;
        System.out.println("Fil resta => compte: " + compte[0]);
    }
    public synchronized int getCompte() {
        return compte[0];
    }
}
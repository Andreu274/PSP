package Exercici7;

public class CompteEnrereThread extends Thread {
    private int segons;

    public CompteEnrereThread(int segons) {
        this.segons = segons;
    }

    public void run() {
        System.out.println("Comencem el compte enrere");
        for (int i = segons; i >= 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                System.out.println("CompteEnrereThread interromput.");
                return;
            }

            // Notificar l'estat
            if (i == 3 * segons / 4) {
                System.out.println("Queden 3/4");
            } else if (i == segons / 2) {
                System.out.println("Queda la meitat");
            } else if (i == segons / 4) {
                System.out.println("Queda 1/4");
            }
        }
        System.out.println("TIMEOUT");
    }
}



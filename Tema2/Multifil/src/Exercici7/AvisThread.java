package Exercici7;

public class AvisThread extends Thread {
    private CompteEnrereThread compteEnrereThread;

    public AvisThread(CompteEnrereThread compteEnrereThread) {
        this.compteEnrereThread = compteEnrereThread;
    }

    public void run() {
        while (compteEnrereThread.isAlive()) {

        }
    }
}





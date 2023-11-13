package Exercici5;

class TicTacPrinter implements Runnable {
    private static final Object lock = new Object(); // Objecte de bloqueig per als dos fils
    private String message; // Missatge a imprimir ("TIC" o "TAC")


    public TicTacPrinter(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                // mentres no sigui el torn del missatge, esperar
                while ((message.equals("TIC")) || (message.equals("TAC"))) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(message); // Imprimir el missatge actual ("TIC" o "TAC")

                lock.notifyAll(); // Notificar a todos els fils que estan
            }
        }
    }
}
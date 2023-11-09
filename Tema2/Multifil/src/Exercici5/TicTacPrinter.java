package Exercici5;

class TicTacPrinter implements Runnable {
    private static final Object lock = new Object(); // Objecte de bloqueig per als dos fils
    private static volatile boolean ticTurn = true; // Variable per controlar el torn de "TIC" i "TAC"
    private String message; // Missatge a imprimir ("TIC" o "TAC")


    public TicTacPrinter(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                // mentres no sigui el torn del missatge, esperar
                while ((message.equals("TIC") && !ticTurn) || (message.equals("TAC") && ticTurn)) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500); // Esperar 200 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(message); // Imprimir el missatge actual ("TIC" o "TAC")
                ticTurn = !ticTurn; // Cambiar el torn
                lock.notifyAll(); // Notificar a todos els fils que estan
            }
        }
    }
}